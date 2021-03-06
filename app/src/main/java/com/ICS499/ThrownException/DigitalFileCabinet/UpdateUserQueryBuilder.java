/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */

package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

class UpdateUserQueryBuilder extends QueryBuilder {
    private DFCAccountDBHelper dbHelper;
    private User accountUser;

    public UpdateUserQueryBuilder(DFCAccountDBHelper dbHelper, User user) {
        this.dbHelper = dbHelper;
        this.accountUser = user;
    }

    @Override
    Object addQuery() {
        //will not be used
        return null;
    }

    @Override
    Object selectQuery() {
        //Will not be used
        return null;
    }

    @Override
    Object deleteQuery() {
        //Will not be used
        return null;
    }

    @Override
    Object updateQuery() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();

        String newPassword = accountUser.getPassword();
        values.put(UserReaderContract.UserEntry.COLUMN_NAME_PASSWORD, newPassword);

        // Which row to update, based on the title
        String selection = UserReaderContract.UserEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(accountUser.getUser_id())};

        Object result = db.update(
                UserReaderContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        db.close();
        return result;
    }
}
