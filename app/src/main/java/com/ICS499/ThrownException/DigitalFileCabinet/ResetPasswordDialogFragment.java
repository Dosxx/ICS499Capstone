/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ResetPasswordDialogFragment extends AppCompatDialogFragment implements View.OnFocusChangeListener{

    private final String TAG = "ResetPasswordDialogFragment";
    private ResetPasswordDialogFragment.ResetDataListener listener;
    /*validate input */
    private final LoginValidator model = new LoginValidator();
    private View resetView;
    private EditText password, passwordConf;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStates) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        resetView = inflater.inflate(R.layout.dialog_reset_password, null);
        password = resetView.findViewById(R.id.resetPasswordEditText);
        passwordConf = resetView.findViewById(R.id.resetPasswordConfEditText);
        builder.setView(resetView)
                .setCancelable(false)
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onFocusChange(resetView, true);
                        if (!model.validatePwdField(String.valueOf(password.getText()))) {
                            Toast.makeText(getContext(), R.string.invalid_password,
                                    Toast.LENGTH_LONG).show();
                            password.setError(getText(R.string.invalid_password));
                            password.requestFocus();
                        } else if (!String.valueOf(password.getText()).equals(String.valueOf(passwordConf.getText()))) {
                            Toast.makeText(getContext(), R.string.password_mismatch, Toast.LENGTH_LONG).show();
                        } else {
                            listener.applyName(String.valueOf(password.getText()));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent loginIntent = new Intent(getContext(), MainActivity.class);
                        startActivity(loginIntent);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ResetPasswordDialogFragment.ResetDataListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        /* Check that the email is a valid email*/
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!model.validateEmailField(password.getText().toString())) {
                        password.setError(getText(R.string.invalid_password));
                        password.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        passwordConf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try {
                    if(!String.valueOf(password.getText()).equals(String.valueOf(passwordConf.getText()))) {
                       passwordConf.setError(getText(R.string.password_mismatch));
                       passwordConf.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface ResetDataListener {
        void applyName(String input);
    }
}