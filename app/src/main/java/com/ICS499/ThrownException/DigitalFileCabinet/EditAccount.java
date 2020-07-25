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

    public EditAccount(User myUser){
        acctUser = myUser;
    }

    public User getAcctUser() {
        return acctUser;
    }

    public void setAcctUser(User acctUser) {
        this.acctUser = acctUser;
    }

    //These methods refer to the isActive field. Not sure if we still need this field.
 /*   public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
*/
    public boolean createAccount(Context context){
    /* Write user data in sql database and set the account to active */
        sqlContext = new QueryContext();
        sqlBuilder = new AddUserQueryBuilder(context.getApplicationContext(), acctUser);
        makeQuery(this.acctUser, context, sqlBuilder);
//        setActive(true);
        return true;
        //TODO get the return to not always be true.
    }

    public boolean deleteAccount() {
        /* Delete the user and account from database */
        // TODO: remove the account data from the database
        return false;
    }

    public boolean login(String email, String pwd, Context context) {
        sqlContext = new QueryContext();
        sqlBuilder = new SelectUserQueryBuilder(context.getApplicationContext());
        makeQuery(this.acctUser, context, sqlBuilder);
        //TODO : validate the query result against user input
        /* */
        return this.acctUser.isAuthenticate(email, pwd);
    }

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
}
