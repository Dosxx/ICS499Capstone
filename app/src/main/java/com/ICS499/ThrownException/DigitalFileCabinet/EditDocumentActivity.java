/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDocumentActivity extends AppCompatActivity implements NameDocumentDialog.DocumentNameListener {
    private Document document;
    private final String TAG = "EditDocumentActivity";
    private String currentImagePath;
    private TextView documentNameTextView;
    private TextView documentLastEditTextView;
    private EditDocument documentEditor;
    private DFCAccountDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_view);

        dbHelper = new DFCAccountDBHelper(getApplicationContext());

        final Button editDocumentName = findViewById(R.id.editDocumentButton);
        final Button closeDocumentName = findViewById(R.id.closeDocumentButton);
        final Button deleteDocumentName = findViewById(R.id.deleteDocumentButton);
        final TextView documentCreateTextView = findViewById(R.id.document_createdDate_TextView);
        final ImageView documentImageView = findViewById(R.id.captureImageOriginal);
        documentNameTextView = findViewById(R.id.document_name_TextView);
        documentLastEditTextView = findViewById(R.id.document_editDate_TextView);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        documentEditor = new EditDocument();

        /* Set up the document view with description*/
        Intent documentIntent = getIntent();
        document = (Document)documentIntent.getSerializableExtra("Document");
        try {
            currentImagePath = document.getFile().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        documentImageView.setImageURI(Uri.fromFile(document.getFile()));

        documentNameTextView.setText(document.getDocumentName());
        documentCreateTextView.setText(document.getCreatedDate());
        documentLastEditTextView.setText(document.getLastEditDate());

        editDocumentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        closeDocumentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHomeActivity();
            }
        });

        deleteDocumentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Delete Document")
                        .setMessage(String.format("Are you sure you want to delete: %S",document.getDocumentName()))
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean deleted = documentEditor.deleteDoc(dbHelper, document);
                                if(deleted) {
                                    Toast.makeText(getApplicationContext(), document.getDocumentName()+" deleted", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Deletion failed!", Toast.LENGTH_LONG).show();
                                }
                                /*return to the home page */
                                returnToHomeActivity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        })
                        .create().show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openDialog() {
        NameDocumentDialog namingDialog = new NameDocumentDialog();
        namingDialog.show(getSupportFragmentManager(), "DocumentNamingActivity");
    }

    @Override
    public void applyName(String documentName) {
        String newName = documentName.replace(" ", "_").trim();
        document.setDocumentName(newName);
        documentNameTextView.setText(newName);
        documentLastEditTextView.setText(document.getLastEditDate());
        boolean updated = documentEditor.updateDoc(dbHelper,document);
        if(updated) {
            Toast.makeText(getApplicationContext(), document.getDocumentName()+" updated", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
        }
    }
    private void returnToHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), DFCHomeActivity.class);
        startActivity(homeIntent);
        finish();
    }
}