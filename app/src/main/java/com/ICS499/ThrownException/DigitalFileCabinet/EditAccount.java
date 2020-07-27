/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;

import org.mindrot.jbcrypt.BCrypt;

public class EditAccount {
    private boolean isActive = false;
    private User acctUser;
    private QueryContext sqlContext;
    private QueryBuilder sqlBuilder;


    public boolean createAccount(DFCAccountDBHelper dbHelper, User user){
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

    public boolean deleteAccount() {
        /* Delete the user and account from database */
        // TODO: remove the account data from the database
        setActive(false);
        return isActive;
    }

//    public boolean login(DFCAccountDBHelper dbHelper, String email, String pwd, Context context) {
//        sqlContext = new QueryContext();
//        /*find user in the database */
//        sqlBuilder = new SelectUserQueryBuilder(dbHelper, email);
//        sqlContext.setQueryBuilder(sqlBuilder);
//        sqlContext.makeQuery();
//
//        //TODO : validate the query result against user input
//        /* */
//        SelectUserQueryBuilder user = (SelectUserQueryBuilder)sqlBuilder.getFoundUser();
//        return
//    }

    private void makeQuery(User user, Context context, QueryBuilder query){
        this.acctUser = user;
        /* decide what query to make */
        sqlContext.setQueryBuilder(query);
        /*add user data into the database */
        sqlContext.makeQuery();
    }

    /* Verify the password match */
    //The password argument should not be hashed
    private boolean verifyHashPassword(String password, String hashPW){
        return BCrypt.checkpw(password, hashPW);
    }

    //These methods refer to the isActive field. Not sure if we still need this field.
    public void setActive(boolean active) {
        isActive = active;
    }

}
