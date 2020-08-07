package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * UI class and home of the digital file cabinet
 */
public class DFCHomeActivity extends AppCompatActivity implements DocumentListAdapter.ItemClickListener {
    public static final String TAG = "DFCHomeActivity";
    private FileCabinet cabinet;
    private DFCAccountDBHelper dbHelper;
    private User accountUser = null;
    private EditAccount account;
    private FileBrowser dfcBrowser;
    private DocumentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cabinet = FileCabinet.getInstance(getApplicationContext());
        dbHelper = new DFCAccountDBHelper(getApplicationContext());
        cabinet.setDfcHelper(dbHelper);
        accountUser = cabinet.getUser();
        account = cabinet.getEditAccount();
        dfcBrowser = new FileBrowser(cabinet);

        Log.d(TAG, cabinet.getDfcHelper().toString());
        final TextView userName = findViewById(R.id.profile_name_textView);
        final TextView userProfile = findViewById(R.id.profileDetailTextView);
        final Button profileButton = findViewById(R.id.profile_button);
        final Button deleteAccount = findViewById(R.id.deleteUserButton);
        final Button logoutButton = findViewById(R.id.logoutButton);
        final Button scanButton = findViewById(R.id.ScanDocumentButton);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final RecyclerView documentListView = findViewById(R.id.documentRecyclerView);
        final ImageView emptyDocImageView = findViewById(R.id.emptyFileImageView);
        final TextView emptyDocTextView = findViewById(R.id.emptyDocTextView);


        /* Set up the document recycler view */
        List<Document> docList = new ArrayList<>(dfcBrowser.makeQuery());
        if(docList.isEmpty()){
            emptyDocImageView.setVisibility(ImageView.VISIBLE);
            emptyDocTextView.setVisibility(TextView.VISIBLE);
        }
        adapter = new DocumentListAdapter(getApplicationContext(), docList);
        documentListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setClickListener(this);
        documentListView.setAdapter(adapter);

        /*show the logged in user name */
        if (accountUser != null) {
            String first = accountUser.getFirstName().toLowerCase();
            String last = accountUser.getLastName().toLowerCase();
            userName.setText(String.format("%s %s",first.substring(0, 1).toUpperCase()+first.substring(1),
                    last.substring(0, 1).toUpperCase()+last.substring(1)));
        }
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountUser != null) {
                    userProfile.setText(accountUser.toString());
                    if (userProfile.getVisibility() == TextView.VISIBLE) {
                        deleteAccount.setVisibility(Button.INVISIBLE);
                        userProfile.setVisibility(TextView.INVISIBLE);
                        logoutButton.setVisibility(Button.INVISIBLE);
                        scanButton.setVisibility(Button.VISIBLE);
                    } else {
                        deleteAccount.setVisibility(Button.VISIBLE);
                        logoutButton.setVisibility(Button.VISIBLE);
                        userProfile.setVisibility(TextView.VISIBLE);
                        scanButton.setVisibility(Button.INVISIBLE);
                    }
                } else {
                    Toast.makeText(cabinet.getContext(), "Profile Not Available",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*confirm this action first*/
                try {
                    builder.setTitle("Delete Account")
                            .setMessage("Are you sure you want to delete the account?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    boolean yes = account.deleteAccount(cabinet.getDfcHelper());
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                }
                            }).create().show();
                } catch (Exception e) {
                    Toast.makeText(cabinet.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(cabinet.getContext(), MainActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                finishAffinity();
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Implement this activity as dialog */
                Intent scanIntent = new Intent(cabinet.getContext(), DocumentScanActivity.class);
                startActivity(scanIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent documentViewIntent = new Intent(getApplicationContext(), EditDocumentActivity.class);
        documentViewIntent.putExtra("Document", (Serializable)adapter.getItem(position));
        startActivity(documentViewIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "CREATE MENU OPTION");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                Log.d(TAG, s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG,"IN THE QUERY TEXT"+s);
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.app_bar_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}