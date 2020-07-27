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
    private User dfcUser;
    private FileCabinet cabinet;
    private DFCAccountDBHelper dbHelper;
    private EditAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        myContext = getApplicationContext();
        dbHelper = new DFCAccountDBHelper(myContext);
        cabinet = FileCabinet.getInstance(myContext);

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
                /* take user to the log in UI */
                Intent loginIntent = new Intent(myContext, MainActivity.class);
                Log.i(TAG, "moving now");
                Toast.makeText(myContext, "Action canceled!",
                        Toast.LENGTH_SHORT).show();
                startActivity(loginIntent);
            }
        });

        /* Validate input */
        final CreateAccountValidator createModel = new CreateAccountValidator();
        createModel.inputValidation(firstNameEditText,lastNameEditText, emailEditText, passwordEditText, password2EditText);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* instantiate a user with the provided input */
                Log.d(TAG,"Creating a user ");
                try {
                    if(createModel.isValid()) {
                        /* Create a user with valid input only */
                        dfcUser = createModel.createUser(myContext);
                        cabinet.setUser(dfcUser);
                        Log.d(TAG, "User created");

                        boolean isCreated = cabinet.getEditAccount().createAccount(dbHelper, cabinet.getUser());
                        Log.d(TAG, cabinet.getUser().getFirstName());
                        if (isCreated) {
                            Log.d(TAG, cabinet.getUser().getFirstName());
                            Toast.makeText(getApplicationContext(), "Welcome "+
                                            dfcUser.getLastName(),
                                            Toast.LENGTH_SHORT).show();
                            /* Redirect the user to the home activity */
                            Intent homeActivityIntent = new Intent(myContext, DFCHomeActivity.class);
//                        Intent homeActivityIntent = new Intent(myContext, DocumentViewActivity.class);
                            startActivity(homeActivityIntent);
                        }else {
                            Toast.makeText(myContext, "Account creating failed", Toast.LENGTH_LONG).show();
                        }
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
}