package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AddDocumentQuery implements QueryBuilder{
    /* Create an instance of the database */
    private DFCAccountDBHelper dbHelper = null;

    private Document document;
    private long documentID;

    @Override
    public void buildQuery() {
        /* Gets the database into write mode */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        /* Create a new map of values, where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME, "");
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME, "");
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL, "");
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD, "");

        /* Insert the new row, returning the primary key value of the new row */
        documentID = db.insert(UserReaderContract.UserEntry.TABLE_NAME, null, values);
    }
    public long getDocumentID(){
        return documentID;
    }
    public Document getDocument(){
        return document;
    }
}
