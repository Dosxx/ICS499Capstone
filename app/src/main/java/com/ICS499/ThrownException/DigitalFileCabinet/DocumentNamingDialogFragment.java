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

import java.util.List;

public class DocumentNamingDialogFragment extends AppCompatDialogFragment {
    private final String TAG = "DocumentNamingDialogFragment";
    private DocumentNameListener listener;
    private List<Document> docLIst;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStates) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

//        Bundle bundle = getArguments();
//        docLIst = new ArrayList<>((ArrayList<Document>)bundle.getSerializable("document"));


        View view = inflater.inflate(R.layout.dialog_naming_doc, null);


        final EditText fileName = view.findViewById(R.id.documentName);
        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = String.valueOf(fileName.getText());
                        if (name.isEmpty()) {
                            Toast.makeText(getContext(), "Must provide a name!", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            listener.applyName(String.valueOf(fileName.getText()));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent homeIntent = new Intent(getContext(), DFCHomeActivity.class);
                        startActivity(homeIntent);
                    }
                });
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