/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SelectDocumentQueryBuilder implements QueryBuilder {

    private DFCAccountDBHelper dbHelper;

    public SelectDocumentQueryBuilder(Context appContext){
        dbHelper = new DFCAccountDBHelper(appContext);
    }

    @Override
    public long addQuery() {
        /* Will not be use */
        return 0;
    }

    @Override
    public void selectQuery() {
        /* Make a query to the database to get the document data */
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DocumentReaderContract.DocumentEntry._ID,
                DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME,
                DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_OBJECT,
                DocumentReaderContract.DocumentEntry.COLUMN_NAME_CREATE_DATE,
                DocumentReaderContract.DocumentEntry.COLUMN_NAME_LOCATION,
                DocumentReaderContract.DocumentEntry.COLUMN_NAME_LAST_MODIFIED
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME + " = ?";
        String[] selectionArgs = { "document_name" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DocumentReaderContract.DocumentEntry._ID + " DESC";

        Cursor cursor = db.query(
                DocumentReaderContract.DocumentEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                sortOrder              // The sort order
        );

        /* retrieve the data from the cursor */
        //TODO: define what data need to be retrieve
        while(cursor.moveToNext()) {
            String document_name = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME));
            String document_object = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_OBJECT));
        }
        cursor.close();
    }
}
