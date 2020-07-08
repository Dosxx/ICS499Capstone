/**
 * Author: Thrown Exceptions
 * This class encapsulates a concrete strategy for adding a user
 * to the user table in SQLite database
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
        this.user = user;
    }
    public void buildQuery() {
        /* Gets the database into write mode */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        /* Create a new map of values, where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME, user.getFirstName());
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME, user.getLastName());
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());

        /* Insert the new row, returning the primary key value of the new row */
        userID = db.insert(UserReaderContract.UserEntry.TABLE_NAME, null, values);
    }
    public long getUserID(){
        return userID;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
