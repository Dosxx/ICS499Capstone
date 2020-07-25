package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UI class and home of the digital file cabinet
 */
public class DFCHomeActivity extends AppCompatActivity {
    private FileCabinet cabinet;
    public static final String TAG = "DFCHomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cabinet = FileCabinet.getInstance(getApplication());

        Log.d(TAG, cabinet.getUser().getFirstName());
//
        final TextView userName = findViewById(R.id.profile_view);
        final Button profileButton = findViewById(R.id.profile_button);
        /*show the logged in user name */
        userName.setText(String.format("%s %s", cabinet.getUser().getFirstName(), cabinet.getUser().getLastName()));

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : display the user profile (first, last name, email)
            }
        });
    }

}