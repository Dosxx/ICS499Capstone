/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 * This class encapsulates the schema for Document
 * table in SQLite database
 */
package com.ICS499.ThrownException.DigitalFileCabinet;
import android.provider.BaseColumns;

public final class DocumentReaderContract {
    /* define the create table query as a constant */
    public static final String CREATE_TABLE =
        "CREATE TABLE "+ DocumentEntry.TABLE_NAME + " ("+
        DocumentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREAMENT NOT NULL, " +
        DocumentEntry.COLUMN_NAME_DOCUMENT_NAME + " TEXT," +
        DocumentEntry.COLUMN_NAME_CREATE_DATE + " TEXT NOT NULL," +
        DocumentEntry.COLUMN_NAME_DOCUMENT_OBJECT+ " BLOB," +
        DocumentEntry.COLUMN_NAME_LAST_MODIFIED + " TEXT," +
        DocumentEntry.COLUMN_NAME_LOCATION + " TEXT);";

    /* Define the drop table query as a constant */
    public static final String DROP_TABLE =
        "DROP TABLE IF EXIST " + DocumentEntry.TABLE_NAME + ";";

    /* Private constructor to prevent creation of this class */
    private DocumentReaderContract(){}

    /* This class defines the document table contents */
    public static class DocumentEntry implements BaseColumns {
        public static final String TABLE_NAME = "Document_table";
        public static final String COLUMN_NAME_DOCUMENT_NAME = "document_name";
        public static final String COLUMN_NAME_CREATE_DATE= "create_date";
        public static final String COLUMN_NAME_DOCUMENT_OBJECT= "document_object";
        public static final String COLUMN_NAME_LAST_MODIFIED= "last_modified";
        public static final String COLUMN_NAME_LOCATION= "location";

    }
}
