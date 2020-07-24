/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class CreateAccountState implements DFCState{
    private boolean isActive = false;
    private User acctUser;


    public CreateAccountState(User myUser){
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
    public void makeQuery() {

    }
}
