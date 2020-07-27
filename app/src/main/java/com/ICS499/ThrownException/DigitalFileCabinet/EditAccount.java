/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import org.mindrot.jbcrypt.BCrypt;

public class EditAccount {
    private boolean isActive = false;
    private User acctUser;
    private QueryContext sqlContext;
    private QueryBuilder sqlBuilder;


    public boolean createAccount(DFCAccountDBHelper dbHelper, User user) throws InterruptedException {
    /* Write user data in sql database and set the account to active */
        acctUser = user;
        sqlContext = new QueryContext();
        sqlBuilder = new AddUserQueryBuilder(dbHelper, acctUser);
        sqlContext.setQueryBuilder(sqlBuilder);
        acctUser = (User) sqlContext.makeQuery();
        if (acctUser.getUser_id() != 0) {
            setActive(true);
        }
        return isActive;
    }

    public boolean isUserRegistered(DFCAccountDBHelper dbHelper){
        if(numberOfRowsInDB(dbHelper) > 1){
            return true;
        }else {
            return false;
        }
    }

    private int numberOfRowsInDB(DFCAccountDBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, UserReaderContract.UserEntry.TABLE_NAME);
    }

    public boolean deleteAccount() {
        /* Delete the user and account from database */
        // TODO: remove the account data from the database
        setActive(false);
        return isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public User getAcctUser() {
        return acctUser;
    }

    public boolean login(DFCAccountDBHelper dbHelper, String email, String pwd) {
        sqlContext = new QueryContext();
        /*find user in the database */
        sqlBuilder = new SelectUserQueryBuilder(dbHelper, email);
        sqlContext.setQueryBuilder(sqlBuilder);
        acctUser = (User) sqlContext.makeQuery();

        /* Authenticate the login request */
        String emailRetrieved = null;
        String pwdRetrieved = null;
        if (acctUser != null) {
            emailRetrieved = acctUser.getEmail();
            pwdRetrieved = acctUser.getPassword();
            if (email.equals(emailRetrieved) && verifyHashPassword(pwd, pwdRetrieved)) {
                return true;
            }
        }
        return false;
    }

    /* Verify the password match */
    private boolean verifyHashPassword(String password, String hashPW){
        return BCrypt.checkpw(password, hashPW);
    }

    //These methods refer to the isActive field. Not sure if we still need this field.
    public void setActive(boolean active) {
        isActive = active;
    }

}
