/*
 * Author: Thrown Exceptions
 * IVS499 Capstone 2020
 * Created: 7/22/2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

class EditDocument {
    private QueryContext sqlContext;
    private QueryBuilder sqlBuilder;

    public void saveDoc(DFCAccountDBHelper dbHelper, Document document) {
        sqlContext = new QueryContext();
        sqlBuilder = new AddDocumentQueryBuilder(dbHelper, document);
        sqlContext.setQueryBuilder(sqlBuilder);
        Document SavedDocument = (Document) sqlContext.makeQuery();
    }

    public Document importDoc() {
        // TODO : implementation goes here
        return null;
    }

    public boolean updateDoc(DFCAccountDBHelper dbHelper, Document document) {
        sqlContext = new QueryContext();
        sqlBuilder = new UpdateDocumentQueryBuilder(dbHelper, document);
        sqlContext.setQueryBuilder(sqlBuilder);
        int result = ((Integer)sqlContext.makeQuery()).intValue();
        // positive non zero is return upon update success
        return result > 0;
    }

    public long getDocumentsCount(DFCAccountDBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "Document_table");
        db.close();
        return count;
    }

    public boolean deleteDoc(DFCAccountDBHelper dbHelper, Document document) {
        /* Delete a single document from the database */
        sqlContext = new QueryContext();
        sqlBuilder = new DeleteDocumentQueryBuilder(dbHelper, document);
        sqlContext.setQueryBuilder(sqlBuilder);
        int result = ((Integer)sqlContext.makeQuery()).intValue();
        // result should be a positive integer greater than 0 on success
        return result > 0;
    }
}
