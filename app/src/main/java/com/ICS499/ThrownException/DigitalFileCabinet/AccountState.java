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

    public void createAccount(){
    /* Write user data in sql database and set the account to active */
        acctUser.makeQuery();
        setActive(true);
    }

    @Override
    public void deleteAccount() {
        /* Delete the user and account from database */
        // TODO: remove the account data from the database
    }

    @Override
    public void login() {/* This state is not responsible for this behavior */}

    @Override
    public void logout() {/* This state is not responsible for this behavior */}

    @Override
    public void openDoc() {/* This state is not responsible for this behavior */}

    @Override
    public void saveDoc() {/* This state is not responsible for this behavior */}

    @Override
    public void createDoc() {/* This state is not responsible for this behavior */}

    @Override
    public void importDoc() {/* This state is not responsible for this behavior */}

    @Override
    public void browse() {/* This state is not responsible for this behavior */}

    @Override
    public void deleteDoc() {/* This state is not responsible for this behavior */}
}
