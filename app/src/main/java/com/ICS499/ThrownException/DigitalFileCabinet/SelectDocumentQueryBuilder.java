/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.HashMap;

public class SelectDocumentQueryBuilder extends QueryBuilder {

    private DFCAccountDBHelper dbHelper;
    private HashMap<String, Document> queryResultList = new HashMap<String, Document>();

    public SelectDocumentQueryBuilder(DFCAccountDBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public Object addQuery() {
        /* Will not be used */
        return null;
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public HashMap<String, Document> selectQuery() {
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
//        String selection = DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME + " = ?";
//        String[] selectionArgs = { "document_name" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DocumentReaderContract.DocumentEntry._ID + " DESC";

        Cursor cursor = db.query(
                DocumentReaderContract.DocumentEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                sortOrder              // The sort order
        );

        /* retrieve the data from the cursor */
        while(cursor.moveToNext()) {
            String document_Id = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry._ID));
            String document_name = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME));
            byte[] document_file = cursor.getBlob(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME));
            String createDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_CREATE_DATE));
            String editDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LAST_MODIFIED));
            String location = cursor.getString(
                    cursor.getColumnIndexOrThrow(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LOCATION));
            Document result = new Document.Builder(document_name)
                    .setDocumentId(Long.parseLong(document_Id))
                    .setCreatedDate(createDate)
                    .setLastEditedDate(editDate)
                    .setFile(new File(location))
                    .setFilePath(location)
                    .build();

            queryResultList.put(document_Id, result);
        }
        cursor.close();
        return queryResultList;
    }

    @Override
    Object deleteQuery() {
        // Will not be user
        return null;
    }

    @Override
    Object updateQuery() {
        // Will not be used
        return null;
    }
}
