/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DFCAccountDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DFCAccount.db";
    private final Context context;
    SQLiteDatabase DFCDatabase;
    private final String TAG = "Database";

    public DFCAccountDBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.d(TAG, " created");
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
        db.execSQL(UserReaderContract.DROP_TABLE);
        db.execSQL(DocumentReaderContract.DROP_TABLE);
        Log.d("Database Operations", "All tables are deleted");
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        if(DFCDatabase != null) {
            DFCDatabase.close();
            SQLiteDatabase.releaseMemory();
            super.close();
        }
    }

    public long getDocumentsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "Document_table");
        db.close();
        return count;
    }
}
