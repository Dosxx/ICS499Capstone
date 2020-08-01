package com.ICS499.ThrownException.DigitalFileCabinet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
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

public class DocumentScanActivity extends AppCompatActivity implements DocumentNamingActivity.DocumentNameListener {
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 2;
    private String currentImagePath;
    private ImageView imageSmall, imageOriginal;
    private TextView documentNameTextView;
    private EditDocument docEditor;
    private DFCAccountDBHelper dbHelper;
    private Document document;
    private FileCabinet cabinet;
    private String docName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        cabinet = FileCabinet.getInstance(getApplicationContext());
        dbHelper = new DFCAccountDBHelper(getApplicationContext());
        cabinet.setDfcHelper(dbHelper);
        docEditor = new EditDocument();

        imageSmall = findViewById(R.id.captureImageSmall);
        imageOriginal = findViewById(R.id.captureImageOriginal);
        documentNameTextView = findViewById(R.id.originalImageTextView);

        findViewById(R.id.ScanDocumentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    if(docName == null) {
                        openDialog();
                    }
                    dispatchCaptureImageIntent();
                    //TODO figure out how to get document name from user here
                }
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

//        String fileName =  docName +"_" + new SimpleDateFormat(
//                            "yyyy_MM_ddd_HH_mm_ss", Locale.getDefault()
//        ).format(new Date());
        String fileName = docName;
        System.out.println("THE DOCUMENT NAME IS :"+ fileName);
        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File directory = new File("/data/data/com.ICS499.ThrownException.DigitalFileCabinet/databases/DFCAccount.db");
        File imageFile = File.createTempFile(
                fileName,"jpg", directory);
        currentImagePath = imageFile.getAbsolutePath();
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
                // Display small image
                imageSmall.setImageBitmap(getScaledBitmap(imageSmall));
                // Display original image
                imageOriginal.setImageBitmap(BitmapFactory.decodeFile(currentImagePath));
                // Following is the captured image file
                // TODO : to be save on the database.
                //TODO : dialog to request a document name
                //TODO : create a document with the file below for saving
                File capturedImageFile = new File(currentImagePath);
            }catch (Exception exception) {
                Toast.makeText(this, exception.getMessage(),
                        Toast.LENGTH_SHORT).show();
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
        DocumentNamingActivity namingDialog = new DocumentNamingActivity();
        namingDialog.show(getSupportFragmentManager(), "DocumentNamingActivity");
    }

    @Override
    public void applyName(String documentName) {
        docName = documentName;
        documentNameTextView.setText(documentName);
    }
}