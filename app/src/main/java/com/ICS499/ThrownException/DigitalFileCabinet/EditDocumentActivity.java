package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditDocumentActivity extends AppCompatActivity implements DocumentNamingActivity.DocumentNameListener {

    final Button editDocumentName = findViewById(R.id.editDocumentButton);
    final Button closeDocumentName = findViewById(R.id.closeDocumentButton);
    final Button deleteDocumentName = findViewById(R.id.deleteDocumentButton);
    final TextView documentNameTextView = findViewById(R.id.document_name_TextView);
    final TextView documentCreateTextView = findViewById(R.id.document_createdDate_TextView);
    final TextView documentLastEditTextView = findViewById(R.id.document_editDate_TextView);
    final ImageView documentImageView = findViewById(R.id.captureImageOriginal);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_view);

        editDocumentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });







    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openDialog() {
        DocumentNamingActivity namingDialog = new DocumentNamingActivity();
        namingDialog.show(getSupportFragmentManager(), "DocumentNamingActivity");
    }

    @Override
    public void applyName(String documentName) {
        documentNameTextView.setText(documentName);
    }
}