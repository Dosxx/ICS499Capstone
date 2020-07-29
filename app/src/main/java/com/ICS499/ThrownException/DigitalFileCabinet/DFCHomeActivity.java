package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * UI class and home of the digital file cabinet
 */
public class DFCHomeActivity extends AppCompatActivity {
    private FileCabinet cabinet;
    private User accountUser = null;
    private EditAccount account;
    public static final String TAG = "DFCHomeActivity";
    private Toolbar toolbar;
    private String[] navigationDrawerItemTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence drawerTitle;
    private CharSequence title;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_home);
        cabinet = FileCabinet.getInstance(getApplication());
        accountUser = cabinet.getUser();
        account = cabinet.getEditAccount();


        final TextView userName = findViewById(R.id.profile_name_textView);
        final TextView userProfile = findViewById(R.id.profileDetailTextView);
        final Button profileButton = findViewById(R.id.profile_button);
        final Button deleteAccount = findViewById(R.id.deleteUserButton);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        /*show the logged in user name */
        if (accountUser != null) {
            userName.setText(String.format("%s %s", accountUser.getFirstName(),
                                accountUser.getLastName()));
        }
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accountUser != null) {
                    userProfile.setText(accountUser.toString());
                    if(userProfile.getVisibility() == TextView.VISIBLE) {
                        deleteAccount.setVisibility(Button.INVISIBLE);
                        userProfile.setVisibility(TextView.INVISIBLE);
                    }else {
                        deleteAccount.setVisibility(Button.VISIBLE);
                        userProfile.setVisibility(TextView.VISIBLE);
                    }
                }else {
                    Toast.makeText(cabinet.getContext(),"Profile Not Available",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*confirm this action first*/
                try{
                    builder.setTitle("Delete Account")
                            .setMessage("Are you sure you want to delete the account?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    boolean yes = account.deleteAccount(cabinet.getDfcHelper());
                                    cabinet.setUser(null);
                                    cabinet.setEditAccount(null);
                                    account = null;
                                    Intent intent = new Intent(cabinet.getContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("EXIT", true);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                }
                            }).create().show();
                }catch (Exception e){
                    Toast.makeText(cabinet.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Fragment view set up
         */
        title = drawerTitle = getTitle();
        navigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

        /* set up the navigation many items*/
        setupToolbar();
        NavigationDataModel[] drawerItem = new NavigationDataModel[7];

        drawerItem[0] = new NavigationDataModel(R.drawable.ic_scanner_24, "Scan");
        drawerItem[1] = new NavigationDataModel(R.drawable.ic_import_export_24, "import");
        drawerItem[2] = new NavigationDataModel(R.drawable.ic_encrypt_24, "Encrypt");
        drawerItem[3] = new NavigationDataModel(R.drawable.ic_decrypt_24, "Decrypt");
        drawerItem[4] = new NavigationDataModel(R.drawable.ic_browse_24, "Browse");
        drawerItem[5] = new NavigationDataModel(R.drawable.ic_edit_24, "Edit");
        drawerItem[6] = new NavigationDataModel(R.drawable.ic_logout_24, "Logout");

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.navigation_list_view, drawerItem);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(drawerToggle);
        setupDrawerToggle();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_dfc_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.edit:
//                //TODO
//                Toast.makeText(cabinet.getContext(), "you click edit", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.encrypt:
//                //TODO
//                Toast.makeText(cabinet.getContext(), "you click encrypt", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.scan:
//                //TODO
//                Toast.makeText(cabinet.getContext(), "you click scan", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.decrypt:
//                //TODO
//                Toast.makeText(cabinet.getContext(), "you click decrypt", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.delete:
//                //TODO
//                Toast.makeText(cabinet.getContext(), "you click delete", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.browse:
//                //TODO
//                Toast.makeText(cabinet.getContext(), "you click browse", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.logout:
//                finishActivity(0);
////                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
////                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }


/** above goes in the onCreate method**/

    private void setupToolbar()throws NullPointerException {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ScanFragment();
                break;
            case 1:
                fragment = new ImportFragment();
                break;
            case 2:
                fragment = new EncryptFragment();
                break;
            case 3:
                fragment = new DecryptFragment();
                break;
            case 4:
                fragment = new BrowseFragment();
                break;
            case 5:
                fragment = new EditFragment();
                break;
            case 6:
                fragment = new LogoutFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);
            setTitle(navigationDrawerItemTitles[position]);
            drawerLayout.closeDrawer(drawerList);
        } else {
            Log.e("DFCHomeActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getSupportActionBar().setTitle(this.title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}