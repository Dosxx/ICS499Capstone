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
    private DFCState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        myContext = getApplicationContext();
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
                /* take user to the app */
                Intent loginIntent = new Intent(myContext, MainActivity.class);
                Log.i(TAG, "moving now");
                Toast.makeText(getApplicationContext(), "Account creation canceled!",
                        Toast.LENGTH_SHORT).show();
                startActivity(loginIntent);
            }
        });

        /* Validate input */
        final CreateAccountModel createModel = new CreateAccountModel();
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
                        state = cabinet.createAccount();
                        Log.d(TAG, state.toString());
                        /* change state to account and create account*/
                        // TODO: need working

                        /* make add account to that database */
                        // TODO: need working

                        Log.d(TAG, cabinet.getUser().getFirstName());
                        Toast.makeText(getApplicationContext(), "Welcome "+dfcUser.getLastName(),
                                Toast.LENGTH_LONG).show();
                        Intent HomeActivityIntent = new Intent(myContext, DFCHomeActivity.class);
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
}