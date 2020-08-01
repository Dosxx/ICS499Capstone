package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DocumentNamingActivity extends AppCompatDialogFragment {
    private final String TAG = "DocumentNamingActivity";
    private EditText fileName;
    private DocumentNameListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStates) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_naming_doc, null);

        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = fileName.getText().toString();
                        if (name.equals("")) {
                            fileName.setError("Must provide a name!");
                        } else {
                            dialog.dismiss();
                            listener.applyName(String.valueOf(fileName.getText()));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Please prove a document name!", Toast.LENGTH_SHORT).show();
                    }
                });

        fileName = view.findViewById(R.id.documentName);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DocumentNameListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DocumentNameListener {
        void applyName(String documentName);
    }


}