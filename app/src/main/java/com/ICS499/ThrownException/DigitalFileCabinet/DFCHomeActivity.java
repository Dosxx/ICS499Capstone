package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UI class and home of the digital file cabinet
 */
public class DFCHomeActivity extends AppCompatActivity {
    User logInUser = (User)getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView userName = findViewById(R.id.profile_view);
        final Button profileButton = findViewById(R.id.profile_button);
        /*show the logged in user name */
        userName.setText(logInUser.getFirstName()+" "+logInUser.getLastName());

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : display the user profile (first, last name, email)
            }
        });
    }
}