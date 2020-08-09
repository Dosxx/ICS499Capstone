package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NameDocumentDialog extends AppCompatDialogFragment {
    private final String TAG = "NamingDialogFragment";
    private DocumentNameListener listener;
    private static Hashtable<String, AtomicInteger> docNameHashTable = new Hashtable<>(25);
    private List<Document> docLIst;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStates) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Bundle bundle = getArguments();
        docLIst = new ArrayList<>((ArrayList<Document>)bundle.getSerializable("document"));
        addNameHashTable(docLIst);
        View view = inflater.inflate(R.layout.dialog_naming_doc, null);

        final EditText fileName = view.findViewById(R.id.documentName);
        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = String.valueOf(fileName.getText());
                        if (name.isEmpty()) {
                            fileName.setError("ERROR");
                            Toast.makeText(getContext(), "Must provide a name!", Toast.LENGTH_LONG).show();
                        } else {
                            //validate that the doc name is not already in used
                            if(findNameInList(name)) {
                                String altName = name.trim().toLowerCase();
                                AtomicInteger value = docNameHashTable.get(altName);
                                if (value != null) {
                                    value.getAndIncrement();
                                }
                                docNameHashTable.put(altName, value);
                                Log.d(TAG, docNameHashTable.toString());
                                String newName = name.trim().concat(String.format("_(%s)",
                                        docNameHashTable.get(altName)));
                                listener.applyName(newName);
                            } else {
                                docNameHashTable.put(name.trim().toLowerCase(), new AtomicInteger(0));
                                listener.applyName(name.trim());
                            }
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("docNameHashTable", docNameHashTable);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        docNameHashTable = (Hashtable<String, AtomicInteger>) savedInstanceState.getSerializable("docNameHashTable");
    }

    public interface DocumentNameListener {
        void applyName(String documentName);
    }

    private boolean findNameInList(String name) {
        return docNameHashTable.containsKey(name.trim().toLowerCase());
    }

    private void addNameHashTable(List<Document> list) {
        for(Document doc : list) {
            docNameHashTable.put(doc.getDocumentName().trim().toLowerCase(), new AtomicInteger(0));
        }
    }
}