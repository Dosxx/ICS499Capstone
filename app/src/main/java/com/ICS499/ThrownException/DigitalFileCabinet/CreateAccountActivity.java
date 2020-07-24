package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountActivity extends AppCompatActivity {

    public static final String TAG = "CreateAccountActivity";
    private final Context myContext = this;
    private String firstName, lastName, email, password, password2;
    private User dfcUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Log.d(TAG, "onCreate: Started.");

        final Button createAccountButton = findViewById(R.id.create_account_button);
        final Button cancelButton = findViewById(R.id.cancel_button);
        final EditText firstNameEditText = findViewById(R.id.fName_input);
        final EditText lastNameEditText = findViewById(R.id.lName_input);
        final EditText emailEditText = findViewById(R.id.email_input);
        final EditText passwordEditText = findViewById(R.id.password_input);
        final EditText password2EditText = findViewById(R.id.confirm_password_input);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* take user to the app */
                Intent loginIntent = new Intent(myContext, MainActivity.class);
                startActivity(loginIntent);
                Log.i(TAG, "moving now");
                Toast.makeText(getApplicationContext(), "Account creation canceled!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /* Validate input */
        firstNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                firstName = firstNameEditText.getText().toString();
                System.out.println(firstName);
                try {
                    if(!isNameValid(firstName)) {
                        firstNameEditText.setError(R.string.invalid_name+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                lastName = lastNameEditText.getText().toString();
                try {
                    if(!isNameValid(lastName)) {
                        lastNameEditText.setError(R.string.invalid_name+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                email = emailEditText.getText().toString();
                try {
                    if(!isEmailValid(email)) {
                        emailEditText.setError(R.string.invalid_email+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                password = passwordEditText.getText().toString();
                try {
                    if(!isPasswordValid(password)) {
                        passwordEditText.setError(R.string.invalid_password+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        password2EditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                password2 = password2EditText.getText().toString();
                try {
                    if(!password.equals(password2)) {
                        password2EditText.setError(R.string.password_mismatch+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /* instantiate a user with the provided input */
                try {
                    if(isNameValid(firstName)&& isNameValid(lastName)&&
                            isEmailValid(email)&& isPasswordValid(password)&& isPasswordValid(password2)) {
                        /* Create a user with valid input only */
                        dfcUser = User.getUserInstance(firstName, lastName, email, password);
                        Log.d(TAG, dfcUser.getFirstName());
                        Toast.makeText(getApplicationContext(), dfcUser.getFirstName(),
                                Toast.LENGTH_LONG).show();
                        Intent HomeActivityIntent = new Intent(myContext, DFCHomeActivity.class);
                        startActivity(HomeActivityIntent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please provide valid input only",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please provide the input",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    /* validation methods */

    private boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(name);
        if(name == null){
            return false;
        }else if(name.length() == 0){
            return false;
        }else if(name.length() > 20){
            return false;
        }else if (matcher.find()) {
            return false;
        }else {
            return true;
        }
    }

    private boolean isEmailValid(String email) {
        if(email == null) {
            return false;
        }
        else if(email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}