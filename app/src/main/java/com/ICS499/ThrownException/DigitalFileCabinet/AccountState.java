/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class AccountState implements DFCState{
    private boolean isActive = false;
    private User acctUser;


    public AccountState(User myUser){
        acctUser = myUser;
    }

    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }

    public User getAcctUser() {
        return acctUser;
    }

    public void setAcctUser(User acctUser) {
        this.acctUser = acctUser;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public DFCState createAccount(){
    /* Write user data in sql database and set the account to active */
        acctUser.makeQuery();
        setActive(true);
        return this;
    }

    @Override
    public DFCState deleteAccount() {
        /* Delete the user and account from database */
        // TODO: remove the account data from the database
        return null;
    }

    @Override
    public DFCState login(String email, String pwd) {return null;}

    @Override
    public DFCState logout() {return null;}

    @Override
    public DFCState openDoc() {return null;}

    @Override
    public DFCState saveDoc() {return null;}

    @Override
    public DFCState createDoc() {return null;}

    @Override
    public DFCState importDoc() {return null;}

    @Override
    public DFCState browse() {return null;}

    @Override
    public DFCState deleteDoc() {return null;}

    @Override
    public DFCState makeQuery() {
        return null;
    }

}
