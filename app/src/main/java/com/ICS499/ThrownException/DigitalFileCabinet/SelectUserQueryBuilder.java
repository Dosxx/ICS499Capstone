/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SelectUserQueryBuilder implements  QueryBuilder{
    /* Make a query to the database to get the user data */

    private DFCAccountDBHelper dbHelper;

    public SelectUserQueryBuilder(Context appContext){
        dbHelper = new DFCAccountDBHelper(appContext);
    }


    @Override
    public long addQuery() {
        /* Will not be used */
        return 0;
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
        //TODO: define what data need retrieving from DB
        while(cursor.moveToNext()) {
            String email = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_EMAIL));
            String pass = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD));
        }
        cursor.close();
    }
}
