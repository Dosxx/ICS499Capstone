/*
 * Author: Thrown Exceptions
 * IVS499 Capstone 2020
 * Created: 7/22/2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.sqlite.SQLiteDatabase;

public class DeleteDocumentQueryBuilder extends QueryBuilder{
    private DFCAccountDBHelper dbHelper;
    private Document document;

    public DeleteDocumentQueryBuilder(DFCAccountDBHelper dbHelper, Document document) {
        this.dbHelper = dbHelper;
        this.document = document;
    }

    @Override
    public Object deleteQuery() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = DocumentReaderContract.DocumentEntry._ID + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(document.getDocumentID()) };

        // Issue SQL statement.
        Object result = db.delete(DocumentReaderContract.DocumentEntry.TABLE_NAME,selection, selectionArgs);
        db.close();
        return result;
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
    public Object updateQuery() {
        //will not be used
        return null;
    }
}
