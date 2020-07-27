/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AddDocumentQueryBuilder extends QueryBuilder{
    /* Create an instance of the database */
    private Document document;
    private DFCAccountDBHelper DFCDatabase;

    public AddDocumentQueryBuilder(DFCAccountDBHelper DBHelper, Document document){
        this.document = document;
        /* Initialize the database helper instance */
        DFCDatabase = DBHelper;
    }

    public Document getDocument(){
        return document;
    }

    @Override
    public Document addQuery() {
        /* Gets the database into write mode */
        SQLiteDatabase db = DFCDatabase.getWritableDatabase();

        /* Create a new map of values, where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME, document.getDocumentName());
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_CREATE_DATE, document.getCreatedDate());
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LAST_MODIFIED, document.getLastEditDate());
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_OBJECT, "");
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LOCATION, document.getFilePath());

        /* Insert the new row, returning the primary key value of the new row */
        document.setDocumentID(db.insert(DocumentReaderContract.DocumentEntry.TABLE_NAME, null, values));
        return document;
    }

    @Override
    public Object selectQuery() {
        /* Will not be used */
        return null;
    }
}
