package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UI class and home of the digital file cabinet
 */
public class DFCHomeActivity extends AppCompatActivity {
    private FileCabinet cabinet;
    private User accountUser = null;
    public static final String TAG = "DFCHomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cabinet = FileCabinet.getInstance(getApplication());
        accountUser = cabinet.getUser();

//        Intent loginIntent = getIntent();
//        Deneme dene = (Deneme)i.getSerializableExtra("sampleObject");

        final TextView userName = findViewById(R.id.profile_name_textView);
        final TextView userProfile = findViewById(R.id.profileDetailTextView);
        final Button profileButton = findViewById(R.id.profile_button);

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
                }else {
                    Toast.makeText(cabinet.getContext(),"Profile Not Available",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}