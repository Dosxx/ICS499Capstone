/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import java.util.HashMap;

public class FileBrowser {
    QueryContext sqlContext;
    QueryBuilder query;
    FileCabinet cabinet;
    HashMap<String, Document> photoDisplay = new HashMap<String, Document>();

    public FileBrowser(FileCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public HashMap<String, Document> makeQuery() {
        sqlContext = new QueryContext();
        query = new SelectDocumentQueryBuilder(cabinet.getDfcHelper());
        sqlContext.setQueryBuilder(query);

        /*add user data into the database */
        photoDisplay = (HashMap<String, Document>)sqlContext.makeQuery();
        return photoDisplay;
    }
}
