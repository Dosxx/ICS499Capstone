package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    public static final String TAG = "CreateAccountActivity";
    private Context myContext;
    private String firstName, lastName, email, password, password2;
    private User dfcUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        myContext = getApplicationContext();
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


//        firstNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                firstName = firstNameEditText.getText().toString();
//                System.out.println(firstName);
//                try {
//                    if(!isNameValid(firstName)) {
//                        firstNameEditText.setError(R.string.invalid_name+"");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                lastName = lastNameEditText.getText().toString();
//                try {
//                    if(!isNameValid(lastName)) {
//                        lastNameEditText.setError(R.string.invalid_name+"");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                email = emailEditText.getText().toString();
//                try {
//                    if(!isEmailValid(email)) {
//                        emailEditText.setError(R.string.invalid_email+"");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                password = passwordEditText.getText().toString();
//                try {
//                    if(!isPasswordValid(password)) {
//                        passwordEditText.setError(R.string.invalid_password+"");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        password2EditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                password2 = password2EditText.getText().toString();
//                try {
//                    if(!password.equals(password2)) {
//                        password2EditText.setError(R.string.password_mismatch+"");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        /* Validate input */
        final CreateAccountModel createModel = new CreateAccountModel();
        createModel.inputValidation(firstNameEditText,lastNameEditText, emailEditText, passwordEditText, password2EditText);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* instantiate a user with the provided input */
                firstName = createModel.getFirst();
                lastName = createModel.getLast();
                email = createModel.getEmail();
                password = createModel.getPwd1();
                password2 = createModel.getPwd2();
                Log.d(TAG,firstName+"\n"+lastName+"\n"+email+"\n"+password+"\n"+password2+"\n"+createModel.isValid());
                try {
                    if(createModel.isValid()) {
                        /* Create a user with valid input only */
                        dfcUser = createModel.createUser(myContext);
                        Log.d(TAG, dfcUser.getFirstName());
                        Toast.makeText(getApplicationContext(), dfcUser.getFirstName(),
                                Toast.LENGTH_LONG).show();
                        Intent HomeActivityIntent = new Intent(dfcUser.getContext(), DFCHomeActivity.class);
                        startActivity(HomeActivityIntent);
                    }else {
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

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
    public User getDfcUser(){
        return dfcUser;
    }
}