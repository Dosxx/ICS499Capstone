/**
 * Author: Thrown Exceptions
 * This class encapsulates the schema for Account
 * table in SQLite database
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.provider.BaseColumns;

public final class AccountReaderContract {
    /* define the create table query as a constant */
    public static final String CREATE_TABLE =
        "CREATE TABLE " + AccountEntry.TABLE_NAME + " ("+
        AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREAMENT NOT NULL, " +
        AccountEntry.COLUMN_NAME_USER_ID + " INTEGER NOT NULL," +
        AccountEntry.COLUMN_NAME_DOCUMENT_ID + " INTEGER NOT NULL," +
        " FOREIGN KEY ("+ AccountEntry.COLUMN_NAME_USER_ID +") REFERENCES "+
        UserReaderContract.UserEntry.TABLE_NAME + "("+ UserReaderContract.UserEntry._ID + "),"+
        " FOREIGN KEY (" + AccountEntry.COLUMN_NAME_DOCUMENT_ID+ ") REFERENCES "+
        DocumentReaderContract.DocumentEntry.TABLE_NAME + "(" +
        DocumentReaderContract.DocumentEntry._ID +"));";

    /* Define the drop table query as a constant */
    public static final String DROP_TABLE =
        "DROP TABLE IF EXIST " + AccountEntry.TABLE_NAME + ";";

    /* Private constructor to prevent creation of this class */
    private AccountReaderContract(){}

    /* This class defines the account table contents */
    public static class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "Account_table";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_DOCUMENT_ID= "document_id";

    }
}
