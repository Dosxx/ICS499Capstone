/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 * This class encapsulates a concrete strategy for adding a user
 * to the user table in SQLite database
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AddUserQueryBuilder implements QueryBuilder{
    /* Create an instance of the database */
    private DFCAccountDBHelper dbHelper;

    /* The id of the added user */
    private long userID;
    private User user;

    public AddUserQueryBuilder(Context appContext, User user){
        dbHelper = new DFCAccountDBHelper(appContext);
        this.user = user;
    }
    public void buildQuery() {

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

    @Override
    public long addQuery() {
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
        return userID;
    }

    @Override
    public void selectQuery() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserReaderContract.UserEntry._ID,
                UserReaderContract.UserEntry.COLUMN_NAME_EMAIL,
                UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserReaderContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
        String[] selectionArgs = { "email" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD + " DESC";

        Cursor cursor = db.query(
                UserReaderContract.UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                sortOrder              // The sort order
        );

        /* retrieve the data from the cursor */
        while(cursor.moveToNext()) {
            String email = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL));
            String pass = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD));
        }
        cursor.close();
    }
}
