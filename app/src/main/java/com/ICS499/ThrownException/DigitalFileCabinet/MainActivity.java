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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The digital file cabinet start here at the login UI
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Context myContext;
    private FileCabinet cabinet = null;
    private DFCAccountDBHelper dbHelper;
    private EditAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DFCAccountDBHelper(this);
        myContext = this;
        cabinet = FileCabinet.getInstance(myContext);
        cabinet.setDfcHelper(dbHelper);
        account = new EditAccount();
        Log.d(TAG, "onCreate: Started.");

        final Button signUpButton = findViewById(R.id.sign_up_button);
        final Button signInButton = findViewById(R.id.sign_in_button);
        final TextView forgotPasswordLabel = findViewById(R.id.forgotPasswordTextView);
        final EditText emailEditText = findViewById(R.id.email_input);
        final EditText passwordEditText = findViewById(R.id.password_input);
        final ProgressBar loadingProgressBar = findViewById(R.id.progressBar);

        /* Show create content view on the click of create account button*/
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Check if the database has a user store, if so disable this activity */
                if(account.isUserRegistered(dbHelper)) {
                    Toast.makeText(myContext, "Please sign in! An account is registered",
                            Toast.LENGTH_LONG).show();
                }else {
                    /* Switch the context to the create activity view */
                    Intent createAccountIntent = new Intent(myContext, CreateAccountActivity.class);
                    cabinet.setEditAccount(account);
                    startActivity(createAccountIntent);
                    Log.i(TAG, "create activity initiated");
                }
            }
        });

        /*validate input */
        final LoginValidator model = new LoginValidator();
        model.inputValidation(emailEditText,passwordEditText);

        /* Defines the action listener on sign in button click */
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (model.isValid()) {
                    if (account.login(dbHelper, model.getEmail(),model.getPwd(), emailEditText, passwordEditText)) {
                        loadingProgressBar.setVisibility(View.VISIBLE);

                        cabinet.setUser(account.getAcctUser());
                        cabinet.setEditAccount(account);
                        Intent homeActivityIntent = new Intent(myContext, DFCHomeActivity.class);
                        startActivity(homeActivityIntent);
                        Toast.makeText(myContext, "Welcome!", Toast.LENGTH_SHORT).show();
                    }else {
                        passwordEditText.setText("");
                        Toast.makeText(myContext, "Login Fail! Please try again", Toast.LENGTH_LONG).show();
                    }
                }else {
                    emailEditText.setError("Required!");
                    passwordEditText.setError("Required!");
                    Toast.makeText(myContext, "Please Provide inputs", Toast.LENGTH_SHORT).show();
                }

            }
        });

        forgotPasswordLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if () {
//                    account.resetPwd(dbHelper, emailEditText.getText().toString());
//                } else {
//                }
                Toast.makeText(myContext, "Sorry! Feature under construction",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

}