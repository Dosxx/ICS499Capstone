package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordDialogFragment.ResetDataListener{
    private final String TAG = "ResetPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        setFinishOnTouchOutside(false);
        openDialog();
    }

    public void openDialog() {
        ResetPasswordDialogFragment resetPwdDialog = new ResetPasswordDialogFragment();
        resetPwdDialog.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void applyName(String input1, String input2) {

    }
}