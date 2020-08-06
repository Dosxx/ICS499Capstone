package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class ResetPasswordActivity extends AppCompatActivity {
    private final String TAG = "ResetPasswordActivity";
    private EditText resetFirstNameInput;
    private EditText resetLastNameInput;
    private EditText resetPasswordInput;
    private EditText resetPassword2Input;
    private Button resetButton;
    private LinearLayout passwordLayout;
    private CreateAccountValidator validator;
    private Button nextButton;
    private User acctUser;
    private AtomicInteger count = new AtomicInteger(3);
    private EditAccount account;
    private DFCAccountDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        validator = new CreateAccountValidator();
        dbHelper = new DFCAccountDBHelper(getApplicationContext());

        /* retrieved fund user in the database */
        Intent userIntent = getIntent();
        acctUser = (User)userIntent.getSerializableExtra("acctUser");
        account = new EditAccount();


        resetFirstNameInput = findViewById(R.id.restFirstNameEditText);
        resetLastNameInput = findViewById(R.id.restLastNameEditText);
        resetPasswordInput = findViewById(R.id.resetPasswordEditText);
        resetPassword2Input = findViewById(R.id.resetPasswordConfEditText);
        resetButton = findViewById(R.id.resetButton);
        nextButton = findViewById(R.id.nextButton);
        passwordLayout = findViewById(R.id.password_inputLayout);

        /* Validate first name input */
        resetFirstNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!validator.validateNameField(String.valueOf(resetFirstNameInput.getText()))) {
                        resetFirstNameInput.setError(getText(R.string.invalid_name));
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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /* Check that the password is valid*/
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
                if(validator.validatePwdField(String.valueOf(resetPasswordInput.getText())) &&
                        String.valueOf(resetPassword2Input.getText()).equals(String.valueOf(resetPasswordInput.getText()))) {
                    account.updatePassword(dbHelper, acctUser);
                }
            }
        });

        resetLastNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* Validate last name input */
                resetLastNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try {
                            if(!validator.validateNameField(String.valueOf(resetLastNameInput.getText()))) {
                                resetLastNameInput.setError(getText(R.string.invalid_name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /* Validate first name input */
                resetFirstNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try {
                            if(!validator.validateNameField(String.valueOf(resetFirstNameInput.getText()))) {
                                resetFirstNameInput.setError(getText(R.string.invalid_name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void afterTextChanged(Editable s) {
                /* Enter key press event handler */
                resetLastNameInput.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_ENTER) {
                            nextButton.requestFocus();
                        }
                    return false;
                    }
                });
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!acctUser.getFirstName().equals(String.valueOf(resetFirstNameInput.getText()))) {
                    resetFirstNameInput.setError("The firstname does NOT MATCH");
                    if(!acctUser.getLastName().equals(String.valueOf(resetLastNameInput.getText()))) {
                        resetLastNameInput.setError("The lastname does NOT MATCH");
                    }
                }else {
                    passwordLayout.setVisibility(LinearLayout.VISIBLE);
                }
                Toast.makeText(getApplicationContext(), String.format("You have %s more tries", count.decrementAndGet()), Toast.LENGTH_LONG).show();
                if(count.get() == 0) {
                    Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });
    }
}