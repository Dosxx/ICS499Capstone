/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class LoginState implements DFCState {


    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }

    @Override
    public DFCState login() {
        // TODO : implementation goes here
        return this;
    }

    @Override
    public DFCState createAccount() {return null;}

    @Override
    public void makeQuery(){
        /* decide what query to make */
        sqlContext = new QueryContext();
        sqlContext.setQueryBuilder(addQuery);
        /*add user data into the database */
        addQuery = new AddUserQueryBuilder(getApplicationContext(), this);
        sqlContext.makeQuery();
        /*Select a user data from database*/
        selectQuery = new SelectUserQueryBuilder(getApplicationContext());
    }

    @Override
    public DFCState deleteAccount() {return null;}

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
