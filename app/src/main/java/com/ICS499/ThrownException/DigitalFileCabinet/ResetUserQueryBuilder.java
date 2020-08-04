package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ResetUserQueryBuilder extends QueryBuilder {
    private DFCAccountDBHelper dbHelper;
    private String email;

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
        User acctUser = null;
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

                acctUser = User.getUserInstance(null, null, null, null);
                acctUser.setUser_id(userID);
                acctUser.setFirstName(firstName);
                acctUser.setLastName(lastName);
                acctUser.setEmail(email);
                break;
            }
            cursor.close();
            /* Return the first user in the database */
            return acctUser;
            //TODO : to be continued...
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

        return null;
    }
}
