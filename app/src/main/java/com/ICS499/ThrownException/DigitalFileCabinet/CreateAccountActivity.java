package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    public static final String TAG = "CreateAccountActivity";
    private final Context myContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Log.d(TAG, "onCreate: Started.");

        final Button createAccountButton = findViewById(R.id.create_account_button);
        final Button cancelButton = findViewById(R.id.cancel_button);



    }
}