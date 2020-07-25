/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AddDocumentQueryBuilder implements QueryBuilder{
    /* Create an instance of the database */
    private DFCAccountDBHelper dbHelper;

    private Document document;
    private long documentID;

    public AddDocumentQueryBuilder(Context appContext, Document document){
        this.document = document;
        /* Initialize the database helper instance */
        dbHelper = new DFCAccountDBHelper(appContext);
    }

    public long getDocumentID(){
        return documentID;
    }
    public void setDocument(Document doc){
        document = doc;
    }

    public Document getDocument(){
        return document;
    }

    @Override
    public long addQuery() {
        /* Gets the database into write mode */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        /* Create a new map of values, where column names are the keys */
        ContentValues values = new ContentValues();
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME, document.getDocumentName());
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_CREATE_DATE, document.getCreatedDate());
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LAST_MODIFIED, document.getLastEditDate());
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_OBJECT, "");
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LOCATION, document.getFilePath());

        /* Insert the new row, returning the primary key value of the new row */
        documentID = db.insert(DocumentReaderContract.DocumentEntry.TABLE_NAME, null, values);
        return documentID;
    }

    @Override
    public void selectQuery() {

    }
}
