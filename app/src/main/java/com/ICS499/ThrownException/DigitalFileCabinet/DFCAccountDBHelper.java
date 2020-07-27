/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DFCAccountDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DFCAccount.db";
    private String database_path;
    private final Context context;
    SQLiteDatabase DFCDatabase;
    private final String TAG = "Database Initialization";

    public DFCAccountDBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        database_path = this.context.getDatabasePath(DATABASE_NAME).getPath();
        Log.d("Database Operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserReaderContract.CREATE_TABLE);
        db.execSQL(DocumentReaderContract.CREATE_TABLE);
        Toast.makeText(context, "All tables are created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "All tables are created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* Simply discard the data and start over on upgrade */
//        db.execSQL(AccountReaderContract.DROP_TABLE);
//        db.execSQL(UserReaderContract.DROP_TABLE);
//        db.execSQL(DocumentReaderContract.DROP_TABLE);
//        Log.d("Database Operations", "All tables are deleted");
//        onCreate(db);
    }

    private boolean checkDatabase() {
        try{
            final String DB_PATH = database_path + DATABASE_NAME;
            final File file = new File(DB_PATH);
            if(file.exists()) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void getDatabase() throws IOException {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outputFIleName = database_path + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outputFIleName);
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void createDatabase() throws  IOException {
        boolean databaseExist = checkDatabase();
        if(!checkDatabase()) {
            this.getReadableDatabase();
            this.close();
        }
        try {
            getDatabase();
        }catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            throw new Error("Error retrieving database");
        }finally {
            this.close();
        }
    }

    @Override
    public synchronized void close() {
        if(DFCDatabase != null) {
            DFCDatabase.close();
            SQLiteDatabase.releaseMemory();
            super.close();
        }
    }
}
