/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 * This class encapsulates the schema for User
 * table in SQLite database
 */

package com.ICS499.ThrownException.DigitalFileCabinet;

import android.provider.BaseColumns;

public final class UserReaderContract {
    /* define the create table query as a constant */
    public static final String CREATE_TABLE =
        "CREATE TABLE " + UserEntry.TABLE_NAME + " ("+
        UserEntry._ID + " INTEGER PRIMARY KEY," +
        UserEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
        UserEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
        UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
        UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    /* Define the drop table query as a constant */
    public static final String DROP_TABLE =
        "DROP TABLE IF EXIST " + UserEntry.TABLE_NAME + ";";

    /* Private constructor to prevent creation of this class */
    private UserReaderContract(){}

    /* This class defines the user table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "User_table";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
