/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

/**
 * The digital file cabinet start here at the login UI
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Context myContext;
    private FileCabinet cabinet;
    private DFCAccountDBHelper dbHelper;
    private EditAccount account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myContext = this;
        cabinet = FileCabinet.getInstance(myContext);
        dbHelper = cabinet.getDfcHelper();
        account = new EditAccount();
        cabinet.setEditAccount(account);
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
            if(cabinet.getEditAccount().isUserRegistered(dbHelper)) {
                Toast.makeText(myContext, "Please sign in! An account is registered",
                        Toast.LENGTH_LONG).show();
            }else {
                /* Switch the context to the create activity view */
                Intent createAccountIntent = new Intent(myContext, CreateAccountActivity.class);
//                cabinet.setEditAccount(account);
                startActivity(createAccountIntent);
                Log.i(TAG, "create activity initiated");
            }
            }
        });

        /*validate input */
        final LoginValidator model = new LoginValidator();

        /* Check that the email is a valid email*/
        emailFocusChanged(emailEditText, model);

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* Check that the email is a valid email*/
                emailFocusChanged(emailEditText, model);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                emailEditText.requestFocus();
            }
        });

        /* check that the password is at least 8 characters long*/
        passwordFocusChanged(passwordEditText, model);

        /* Enter key press event handler */
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                   return signInButton.requestFocus();
                }
                return false;
            }
        });

        /* Defines the action listener on sign in button click */
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (model.validateEmailField(emailEditText.getText().toString()) &&
                    model.validatePwdField(passwordEditText.getText().toString())) {
                if (cabinet.getEditAccount().login(dbHelper, emailEditText.getText().toString(),
                        passwordEditText.getText().toString(), emailEditText, passwordEditText)) {
                    loadingProgressBar.setVisibility(View.VISIBLE);

                    cabinet.setUser(cabinet.getEditAccount().getAcctUser());
                    Intent homeActivityIntent = new Intent(myContext, DFCHomeActivity.class);
                    startActivity(homeActivityIntent);
                    Toast.makeText(cabinet.getContext(), "Welcome!", Toast.LENGTH_LONG).show();
                }else {
                    passwordEditText.setText("");
                    Toast.makeText(cabinet.getContext(), "Login Fail! Please try again", Toast.LENGTH_LONG).show();
                }
            }else {
                emailEditText.setError("Required!");
                passwordEditText.setError("Required!");
                Toast.makeText(cabinet.getContext(), "Please Provide inputs", Toast.LENGTH_LONG).show();
            }
            }
        });

        forgotPasswordLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (model.validateEmailField(String.valueOf(emailEditText.getText()))) {
                if(cabinet.getEditAccount().resetPwd(dbHelper, String.valueOf(emailEditText.getText()))) {
                    //go to reset activity
                    Intent resetPasswordIntent = new Intent(cabinet.getContext(), ResetPasswordActivity.class);
                    cabinet.setUser(cabinet.getEditAccount().getAcctUser());
                    resetPasswordIntent.putExtra("acctUser", (Serializable)account.getAcctUser());
                    startActivity(resetPasswordIntent);
                }else {
                    Toast.makeText(cabinet.getContext(), "No account is registered/ Email does not match", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(cabinet.getContext(), R.string.invalid_email, Toast.LENGTH_LONG).show();
            }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void emailFocusChanged(final EditText editText, final LoginValidator validator) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!validator.validateEmailField(editText.getText().toString())) {
                        editText.setError(getText(R.string.invalid_email));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void passwordFocusChanged(final EditText editText, final LoginValidator validator) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!validator.validatePwdField(editText.getText().toString())) {
                        editText.setError(getText(R.string.invalid_password));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}