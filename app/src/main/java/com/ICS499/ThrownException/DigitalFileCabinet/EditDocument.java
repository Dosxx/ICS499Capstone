package com.ICS499.ThrownException.DigitalFileCabinet;

/*
 * Author: Thrown Exceptions
 * IVS499 Capstone 2020
 * Created: 7/22/2020
 */
class EditDocument {
    private Document document;
    private QueryContext sqlContext;
    private QueryBuilder sqlBuilder;

    /* Method that apply to document are aggregated in this class*/
    public Document openDoc() {
        // TODO : implementation goes here
        return null;
    }

    public void saveDoc(DFCAccountDBHelper dbHelper, Document document) {
        sqlContext = new QueryContext();
        sqlBuilder = new AddDocumentQueryBuilder(dbHelper, document);
        sqlContext.setQueryBuilder(sqlBuilder);
        this.document = (Document) sqlContext.makeQuery();
    }

    public Document createDoc() {
        // TODO: implementation goes here
        return null;
    }

    public Document importDoc() {
        // TODO : implementation goes here
        return null;
    }

    public boolean deleteDoc(DFCAccountDBHelper dbHelper, Document document) {
        sqlContext = new QueryContext();
        sqlBuilder = new DeleteDocumentQueryBuilder(dbHelper, document);
        sqlContext.setQueryBuilder(sqlBuilder);
        int result = ((Integer)sqlContext.makeQuery()).intValue();
        // result should be a positive integer greater than 0 on success
        return result > 0;
    }
}
