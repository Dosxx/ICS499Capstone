/**
 * Author: Thrown Exceptions
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AddUserQuery implements QueryBuilder{
    /* Create an instance of the database */
    private DFCAccountDBHelper dbHelper = null;

    /* The id of the added user */
    private long userID;
    private User user;

    public AddUserQuery(Context appContext, User user){
        dbHelper = new DFCAccountDBHelper(appContext);
    }
    public void buildQuery(QueryContext context) {
        /* Gets the database into write mode */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        /* Create a new map of values, where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME,"");
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME,"");
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL,"");
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD,"");

        /* Insert the new row, returning the primary key value of the new row */
        userID = db.insert(UserReaderContract.UserEntry.TABLE_NAME, null, values);
    }
    public long getUserID(){
        return userID;
    }
}
