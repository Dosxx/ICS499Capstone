/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordDialogFragment.ResetDataListener{
    private final String TAG = "ResetPasswordActivity";
    private CreateAccountValidator validator;
    private FileCabinet cabinet;
    private User acctUser;
    private AtomicInteger count = new AtomicInteger(3);
    private EditAccount account;
    private DFCAccountDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        validator = new CreateAccountValidator();
        cabinet = FileCabinet.getInstance(getApplication());
        /* retrieved fund user in the database */
        Intent userIntent = getIntent();
        account = cabinet.getEditAccount();
        dbHelper = cabinet.getDfcHelper();
        acctUser = (User)userIntent.getSerializableExtra("acctUser");

        final EditText resetFirstNameInput = findViewById(R.id.restFirstNameEditText);
        final EditText resetLastNameInput = findViewById(R.id.restLastNameEditText);
        final Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!acctUser.getFirstName().equals(String.valueOf(resetFirstNameInput.getText())) ||
                        !acctUser.getLastName().equals(String.valueOf(resetLastNameInput.getText()))) {
                    resetFirstNameInput.setError(getText(R.string.incorrect_firstName));
                    resetLastNameInput.setError(getText(R.string.incorrect_lastName));
                    resetFirstNameInput.requestFocus();
                    Toast.makeText(getApplicationContext(), String.format("You have %s more tries", count.decrementAndGet()),
                            Toast.LENGTH_LONG).show();

                    if(count.get() == 0) {
                        Toast.makeText(getApplicationContext(), "Password cannot be reset", Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                }else {
                    openDialog();
                }
            }
        });

        resetLastNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
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
    }
    public void openDialog() {
        ResetPasswordDialogFragment resetDialog = new ResetPasswordDialogFragment();
        resetDialog.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void applyName(String input) {
        acctUser.setPassword(validator.hashPassword(input));
        account.updatePassword(dbHelper, acctUser);
        if(account.updatePassword(dbHelper, acctUser)) {
            Intent logInIntent = new Intent(cabinet.getContext(), MainActivity.class);
            startActivity(logInIntent);
            logInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            logInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finishAffinity();
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}