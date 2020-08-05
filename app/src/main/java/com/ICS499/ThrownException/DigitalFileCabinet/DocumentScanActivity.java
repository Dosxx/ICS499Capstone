package com.ICS499.ThrownException.DigitalFileCabinet;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DocumentScanActivity extends AppCompatActivity implements DocumentNamingDialogFragment.DocumentNameListener {
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 2;

    private String currentImagePath;
    private ImageView imageOriginal;
    private TextView documentNameTextView;
    private EditDocument docEditor;
    private DFCAccountDBHelper dbHelper;
    private FileCabinet cabinet;
    private String docName = null;
    private android.app.AlertDialog.Builder builder;
    private final String TAG = "DocumentScanActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        cabinet = FileCabinet.getInstance(getApplicationContext());
        dbHelper = new DFCAccountDBHelper(getApplicationContext());
        cabinet.setDfcHelper(dbHelper);
        docEditor = new EditDocument();

        builder = new android.app.AlertDialog.Builder(this);

        imageOriginal = findViewById(R.id.captureImageOriginal);
        documentNameTextView = findViewById(R.id.originalImageTextView);
        final Button scanDocumentButton = findViewById(R.id.ScanDocumentButton);

        scanDocumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    private void dispatchCaptureImageIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try{
                imageFile = createImageFile();
            }catch (IOException exception){
                Toast.makeText(this, exception.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
            if(imageFile != null) {
                Uri imageUri = FileProvider.getUriForFile(
                        this,
                        "com.ICS499.ThrownException.DigitalFileCabinet.fileprovider",
                        imageFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE);
            }
        }
    }

    private File createImageFile() throws IOException {

        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                docName,"jpg", directory);
        currentImagePath = imageFile.getAbsolutePath();
        Log.d(TAG, currentImagePath);
        return imageFile;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_PERMISSIONS && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                dispatchCaptureImageIntent();
            } else {
                Toast.makeText(this, "Not all permissions granted",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            try{
                // Display original image
                imageOriginal.setImageBitmap(BitmapFactory.decodeFile(currentImagePath));
                documentNameTextView.setText(docName);
                // Following is the captured image file
                File capturedImageFile = new File(currentImagePath);
                /* create a document here with the image file created */
                String today = new SimpleDateFormat(
                        "yyyy_MM_ddd_HH_mm_ss", Locale.getDefault()
                ).format(new Date());
                Document document = new Document.Builder(docName)
                        .setCreatedDate(today)
                        .setLastEditedDate(today)
                        .setFilePath(currentImagePath)
                        .setFile(capturedImageFile)
                        .build();
                docEditor.saveDoc(dbHelper, document);
                /* Ask user to return home */
                builder.setTitle("Confirm Action")
                        .setMessage("Return Home?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(cabinet.getContext(), DFCHomeActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                Intent intent = new Intent(cabinet.getContext(), DocumentScanActivity.class);
                                startActivity(intent);
                            }
                        }).create().show();

            }catch (Exception exception) {
                Toast.makeText(cabinet.getContext(), exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* image scaling functions */
    private Bitmap getScaledBitmap(ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        int scaleFactor = Math.min(
                options.outWidth / imageView.getWidth(),
                options.outHeight / imageView.getHeight()
        );
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable = true;
        return BitmapFactory.decodeFile(currentImagePath, options);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openDialog() {
        DocumentNamingDialogFragment namingDialog = new DocumentNamingDialogFragment();
        namingDialog.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void applyName(String documentName) {
        docName = documentName.trim().toUpperCase();
        if(ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    DocumentScanActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    REQUEST_CODE_PERMISSIONS
            );
        } else {
            dispatchCaptureImageIntent();
        }
    }
}