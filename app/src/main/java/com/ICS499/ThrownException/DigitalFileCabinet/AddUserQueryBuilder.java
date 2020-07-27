/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 * This class encapsulates a concrete strategy for adding a user
 * to the user table in SQLite database
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AddUserQueryBuilder extends QueryBuilder{
    /* Create an instance of the database */
    private DFCAccountDBHelper DFCDatabase;
    private User user;

    public AddUserQueryBuilder(DFCAccountDBHelper DBHelper, User user){
        this.user = user;
        this.DFCDatabase = DBHelper;
    }

    public User getUser(){
        return user;
    }

    @Override
    public Object addQuery() {
        /* Gets the database into write mode */
        SQLiteDatabase db = DFCDatabase.getWritableDatabase();

        /* Create a new map of values, where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME, user.getFirstName());
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME, user.getLastName());
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());

        /* Insert the new row, returning the primary key value of the new row */
        user.setUser_id(db.insert(UserReaderContract.UserEntry.TABLE_NAME, null, values));
        return null;
    }

    @Override
    public Object selectQuery() {
       /* Will not be used*/
        return null;
    }
}
