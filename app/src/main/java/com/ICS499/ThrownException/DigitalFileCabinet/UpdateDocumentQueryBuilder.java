package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class UpdateDocumentQueryBuilder extends QueryBuilder{
    private DFCAccountDBHelper dbHelper;
    private Document document;

    public UpdateDocumentQueryBuilder(DFCAccountDBHelper dbHelper, Document document) {
        this.dbHelper = dbHelper;
        this.document = document;
    }

    @Override
    public Object addQuery() {
        //Will not be used
        return null;
    }

    @Override
    public Object selectQuery() {
        //Will not be used
        return null;
    }

    @Override
    public Object deleteQuery() {
        //Will not be used
        return null;
    }

    @Override
    public Object updateQuery() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();

        String newDocName = document.getDocumentName();
        String newEditDate = document.getLastEditDate();
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_DOCUMENT_NAME, newDocName);
        values.put(DocumentReaderContract.DocumentEntry.COLUMN_NAME_LAST_MODIFIED, newEditDate);

        // Which row to update, based on the title
        String selection = DocumentReaderContract.DocumentEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(document.getDocumentID()) };

        return db.update(
                DocumentReaderContract.DocumentEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
