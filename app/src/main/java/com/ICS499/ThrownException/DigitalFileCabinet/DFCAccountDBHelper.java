/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DFCAccountDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DFCAccount.db";

    public DFCAccountDBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(AccountReaderContract.CREATE_TABLE);
        db.execSQL(UserReaderContract.CREATE_TABLE);
        db.execSQL(DocumentReaderContract.CREATE_TABLE);
        Log.d("Database Operations", "All tables are created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* Simply discard the data and start over on upgrade */
//        db.execSQL(AccountReaderContract.DROP_TABLE);
        db.execSQL(UserReaderContract.DROP_TABLE);
        db.execSQL(DocumentReaderContract.DROP_TABLE);
        Log.d("Database Operations", "All tables are deleted");
        onCreate(db);
    }
}
