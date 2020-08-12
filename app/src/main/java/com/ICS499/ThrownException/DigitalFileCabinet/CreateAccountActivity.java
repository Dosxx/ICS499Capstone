/*
 * Author: Thrown Exceptions
 * IVS499 Capstone 2020
 * Created: 7/22/2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    public static final String TAG = "CreateAccountActivity";
    private Context myContext;
    private User dfcUser;
    private FileCabinet cabinet;
    private DFCAccountDBHelper dbHelper;
    private EditAccount account;
    private CreateAccountValidator createModel;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText password2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        myContext = getApplicationContext();
        dbHelper = new DFCAccountDBHelper(getApplicationContext());
        cabinet = FileCabinet.getInstance(getApplication());

        Log.d(TAG, "onCreate: Started.");

        final Button createAccountButton = findViewById(R.id.create_account_button);
        final Button cancelButton = findViewById(R.id.cancel_button);
        firstNameEditText = findViewById(R.id.fName_input);
        lastNameEditText = findViewById(R.id.lName_input);
        emailEditText = findViewById(R.id.email_input);
        passwordEditText = findViewById(R.id.password_input);
        password2EditText = findViewById(R.id.confirm_password_input);
        final LinearLayout loadingProgressBar = findViewById(R.id.createProgressLayout);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* take user to the log in UI */
                Intent loginIntent = new Intent(myContext, MainActivity.class);
                Log.i(TAG, "moving now");
                Toast.makeText(myContext, "Action canceled!",
                        Toast.LENGTH_SHORT).show();
                startActivity(loginIntent);
            }
        });

        /* Validate input */
        createModel = new CreateAccountValidator();

        /* Validate first name input */
        firstNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!createModel.validateNameField(String.valueOf(firstNameEditText.getText()))) {
                        firstNameEditText.setError(getText(R.string.invalid_name));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* Validate last name input */
        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!createModel.validateNameField(String.valueOf(lastNameEditText.getText()))) {
                        lastNameEditText.setError(getText(R.string.invalid_name));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* Check that the email is a valid email*/
        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!createModel.validateEmailField(String.valueOf(emailEditText.getText()))) {
                        emailEditText.setError(getText(R.string.invalid_email));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* check that the password is at least 8 characters long*/
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!createModel.validatePwdField(String.valueOf(passwordEditText.getText()))) {
                        passwordEditText.setError(getText(R.string.invalid_password));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* check that both provided password match*/
        password2EditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!String.valueOf(password2EditText.getText()).equals(String.valueOf(passwordEditText.getText()))) {
                       password2EditText.setError(getText(R.string.password_mismatch));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /* Enter key press event handler */
        password2EditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return createAccountButton.requestFocus();
                }
                return false;
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /* instantiate a user with the provided input */
            try {
                if(isValidInputs()) {
                    /* Create a user with valid input only */
                    loadingProgressBar.setVisibility(LinearLayout.VISIBLE);
                    account = cabinet.getEditAccount();
                    dfcUser = account.createUser(String.valueOf(firstNameEditText.getText()),
                            String.valueOf(lastNameEditText.getText()),
                            String.valueOf(emailEditText.getText()),
                            createModel.hashPassword(String.valueOf(passwordEditText.getText())));
                    cabinet.setUser(dfcUser);

                    account.createAccount(dbHelper, cabinet.getUser());

                    Toast.makeText(myContext, "Account created successfully", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Welcome "+
                                    dfcUser.getLastName(),
                                    Toast.LENGTH_SHORT).show();
                    /* Redirect the user to the home activity */
                    Intent homeActivityIntent = new Intent(myContext, DFCHomeActivity.class);
                    startActivity(homeActivityIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "Please provide valid input only!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    private boolean isValidInputs() {
        return createModel.validateNameField(String.valueOf(firstNameEditText.getText())) &&
                createModel.validateNameField(String.valueOf(lastNameEditText.getText())) &&
                createModel.validateEmailField(String.valueOf(emailEditText.getText())) &&
                createModel.validatePwdField(String.valueOf(passwordEditText.getText())) &&
                String.valueOf(password2EditText.getText()).equals(String.valueOf(passwordEditText.getText()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}