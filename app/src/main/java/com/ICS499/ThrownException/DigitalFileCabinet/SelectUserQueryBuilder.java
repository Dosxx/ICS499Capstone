/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SelectUserQueryBuilder extends QueryBuilder{
    /* Make a query to the database to get the user data */

    private DFCAccountDBHelper dbHelper;
    private User foundUser;
    private String email;

    public SelectUserQueryBuilder(DFCAccountDBHelper dbHelper, String email){
        this.dbHelper = dbHelper;
        this.email = email;
    }

    public User getFoundUser() {
        return foundUser;
    }

    @Override
    public Object addQuery() {
        /* Will not be used */
        return null;
    }

    @Override
    public Object selectQuery() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserReaderContract.UserEntry._ID,
                UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME,
                UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME,
                UserReaderContract.UserEntry.COLUMN_NAME_EMAIL,
                UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD
        };

        // Filter results WHERE "email" = 'email' and "Password" = 'password'
//        String selection = UserReaderContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
//        String[] selectionArgs = {email};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserReaderContract.UserEntry._ID+ " DESC";

//        Cursor cursor = db.rawQuery("SELECT * FROM "+
//                UserReaderContract.UserEntry.TABLE_NAME,
//                null);

        Cursor cursor = db.query(
                UserReaderContract.UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                sortOrder              // The sort order
        );

        /* retrieve the data from the cursor */
        if(cursor.getCount() < 1){
            return null;
        }else {
            while(cursor.moveToNext()) {
                long userID = cursor.getLong(cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry._ID));
                String firstName = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME));
                String lastName = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_LAST_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL));
                String password = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD));
                foundUser = User.getUserInstance(firstName, lastName, email, password);
                foundUser.setUser_id(userID);
                break;
            }
            cursor.close();
            /* Return the first user in the database */
            return foundUser;
        }
    }
}
