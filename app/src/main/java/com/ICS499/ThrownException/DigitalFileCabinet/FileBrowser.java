/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FileBrowser {
    QueryContext sqlContext;
    QueryBuilder query;
    FileCabinet cabinet = FileCabinet.getInstance(null);
    EditDocument docSelector = cabinet.getEditDocument();
    Document doc;
    DFCAccountDBHelper dbHelper = new DFCAccountDBHelper(cabinet.getApplicationContext());
    List photoDisplay = new ArrayList<Document>();
    private boolean isBrowsing = true;
    public void browse() {
        long fileCount = dbHelper.getDocumentsCount();
        for(long index = 0; index < fileCount; index++){
            doc = makeQuery();

        }
        // TODO implement browsing functionality here
    }

    public Document makeQuery() {
        //TODO implement querying to facilitate browsing.
//            docSelector.makeQuery(null, fetchDoc);


        sqlContext.setQueryBuilder(query);
        /*add user data into the database */
        sqlContext.makeQuery();
        return null;
    }

    public void setIsBrowsing(Boolean isBrowsing){this.isBrowsing = isBrowsing;}
    public Boolean getIsBrowsing(){return isBrowsing;}

    public void setList(ArrayList list){this.photoDisplay = list;}
    public List getList() {return photoDisplay;}
}
