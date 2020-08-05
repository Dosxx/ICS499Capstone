package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    private final String TAG = "ResetPasswordActivity";
    private EditText resetFirstNameInput;
    private EditText resetLastNameInput;
    private EditText resetPasswordInput;
    private EditText resetPassword2Input;
    private Button resetButton;
    private CreateAccountValidator validator;
    private User acctUser;
    private boolean isValidUser;
    private EditAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        validator = new CreateAccountValidator();

        /* retrieved fund user in the database */
        Intent userIntent = getIntent();
        acctUser = (User)userIntent.getSerializableExtra("acctUser");


        resetFirstNameInput = findViewById(R.id.restFirstNameEditText);
        resetLastNameInput = findViewById(R.id.restLastNameEditText);
        resetPasswordInput = findViewById(R.id.resetPasswordEditText);
        resetPassword2Input = findViewById(R.id.resetPasswordConfEditText);
        resetButton = findViewById(R.id.resetButton);

        /* Validate first name input */
        resetFirstNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!validator.validateNameField(String.valueOf(resetFirstNameInput.getText()))) {
                        resetFirstNameInput.setError(getText(R.string.invalid_name));
                        isValidUser = showPasswordInputField();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* Validate last name input */
        resetLastNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!validator.validateNameField(String.valueOf(resetLastNameInput.getText()))) {
                        resetLastNameInput.setError(getText(R.string.invalid_name));
                        isValidUser = showPasswordInputField();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* Check that the email is a valid email*/
        resetPasswordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!validator.validateEmailField(String.valueOf(resetPasswordInput.getText()))) {
                        resetPasswordInput.setError(getText(R.string.invalid_email));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        /* check that both provided password match*/
        resetPassword2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!String.valueOf(resetPassword2Input.getText()).equals(String.valueOf(resetPasswordInput.getText()))) {
                        resetPassword2Input.setError(getText(R.string.password_mismatch));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidUser && validator.validatePwdField(String.valueOf(resetPasswordInput.getText())) &&
                        String.valueOf(resetPassword2Input.getText()).equals(String.valueOf(resetPasswordInput.getText()))) {
                    //TODO:
                }
            }
        });
    }

    private boolean showPasswordInputField(){
        if(acctUser.getFirstName().equals(resetFirstNameInput) && acctUser.getLastName().equals(resetLastNameInput)) {
            resetPasswordInput.setVisibility(EditText.VISIBLE);
            resetPassword2Input.setVisibility(EditText.VISIBLE);
            return true;
        }else
            return false;
    }


}