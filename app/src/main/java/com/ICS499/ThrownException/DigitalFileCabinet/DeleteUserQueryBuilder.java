/*
 * Author: Thrown Exceptions
 * IVS499 Capstone 2020
 * Created: 7/22/2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.sqlite.SQLiteDatabase;

public class DeleteUserQueryBuilder extends QueryBuilder {

    private User user;
    private DFCAccountDBHelper DFCDatabase;

    public DeleteUserQueryBuilder(DFCAccountDBHelper helper, User user) {
        this.user = user;
        DFCDatabase = helper;
    }

    @Override
    public Object addQuery() {
        // Will not be use
        return null;
    }

    @Override
    public Object selectQuery() {
        //will not be use
        return null;
    }

    @Override
    public Object deleteQuery() {
        SQLiteDatabase db = DFCDatabase.getWritableDatabase();
        // Define 'where' part of query.
        String selection = UserReaderContract.UserEntry.COLUMN_NAME_FIRST_NAME + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = { user.getFirstName() };

        // Issue SQL statement.
        //Upon deletion of an account all documents will be deleted as well
        db.delete(DocumentReaderContract.DocumentEntry.TABLE_NAME,null, null);
        Object result = db.delete(UserReaderContract.UserEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
        return result;
    }

    @Override
    public Object updateQuery() {
        //Will not be used
        return null;
    }
}
