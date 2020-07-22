/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private final Context myContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Started.");

        final Button signUpButton = findViewById(R.id.sign_up_button);
        final Button signInButton = findViewById(R.id.sign_in_button);
        final TextView forgotPasswordLabel = findViewById(R.id.forgotPasswordTextView);

        /* Show create content view on the click of create account button*/
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Switch the context to the create activity view */
                Intent intent = new Intent(myContext, CreateAccountActivity.class);
                startActivity(intent);
                Log.i(TAG, "moving now");

            }
        });

        /* Defines the action listener on sign in button click */
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : handle the log in process in here
            }
        });
    }
}