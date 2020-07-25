/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;

public class EditAccount {
    private boolean isActive = false;
    private User acctUser;

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
    public boolean createAccount(){
    /* Write user data in sql database and set the account to active */
        acctUser.makeQuery();
//        setActive(true);
        return true;
        //TODO get the return to not always be true.
    }

    public boolean deleteAccount() {
        /* Delete the user and account from database */
        // TODO: remove the account data from the database
        return false;
    }

    public boolean login(String email, String pwd) {return false;}

    public void makeQuery(User user, Context context){
        this.acctUser = user;
        /* decide what query to make */
        QueryContext sqlContext = new QueryContext();
        QueryBuilder userQuery = new AddUserQueryBuilder(context.getApplicationContext(), acctUser);
        sqlContext.setQueryBuilder(userQuery);
        /*add user data into the database */
        sqlContext.makeQuery();
        /*Select a user data from database*/
        selectQuery = new SelectUserQueryBuilder(getApplicationContext());
    }

}
