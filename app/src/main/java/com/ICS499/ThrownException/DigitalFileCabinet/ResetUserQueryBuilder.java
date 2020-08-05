package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ResetUserQueryBuilder extends QueryBuilder {
    private DFCAccountDBHelper dbHelper;
    private String email;
    private User regUser = null;

    public ResetUserQueryBuilder(DFCAccountDBHelper dbHelper, String email) {
        this.dbHelper = dbHelper;
        this.email = email;
    }
    @Override
    Object addQuery() {
        //Will not be used
        return null;
    }

    @Override
    public Object selectQuery() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Filter results WHERE "email" = email

        String[] projection = {
                UserReaderContract.UserEntry._ID,
                UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME,
                UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME,
                UserReaderContract.UserEntry.COLUMN_NAME_EMAIL};

        String selection = UserReaderContract.UserEntry.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                UserReaderContract.UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                null           // The sort order
                );
        if(cursor.getCount() < 1){
            /*no matching email found exit the reset process */
            return null;
        }else {
            while (cursor.moveToNext()) {
                long userID = cursor.getLong(cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry._ID));
                String firstName = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME));
                String lastName = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL));

                regUser = User.getUserInstance(null, null, null, null);
                regUser.setUser_id(userID);
                regUser.setFirstName(firstName);
                regUser.setLastName(lastName);
                regUser.setEmail(email);
                break;
            }
            cursor.close();
            /* Return the first user in the database */
            return regUser;
        }
    }

    @Override
    Object deleteQuery() {
        //Will not be used
        return null;
    }

    @Override
    Object updateQuery() {
        /*Update the user pwd when and only when email, first and last names match */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();

        String newPassword = regUser.getPassword();
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD, newPassword);

        // Which row to update, based on the title
        String selection = UserReaderContract.UserEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(regUser.getUser_id()) };

        return db.update(
                UserReaderContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
