package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AddDocumentQuery implements QueryBuilder{
    /* Create an instance of the database */
    private DFCAccountDBHelper dbHelper = null;

    private Document document;
    private long documentID;

    @Override
    public void buildQuery() {
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
}
