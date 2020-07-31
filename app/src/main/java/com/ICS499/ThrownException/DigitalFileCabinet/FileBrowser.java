/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import java.util.ArrayList;

public class FileBrowser {
    QueryContext sqlContext;
    QueryBuilder query;
    FileCabinet cabinet;
    Document doc;
    DFCAccountDBHelper dbHelper = new DFCAccountDBHelper(cabinet.getApplicationContext());
    ArrayList<Document> photoDisplay = new ArrayList<Document>();

    public FileBrowser(FileCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public ArrayList<Document> makeQuery() {

        query = new SelectDocumentQueryBuilder(cabinet.getDfcHelper());
        sqlContext.setQueryBuilder(query);
        /*add user data into the database */
        photoDisplay = (ArrayList<Document>)sqlContext.makeQuery();
        return photoDisplay;
    }

}
