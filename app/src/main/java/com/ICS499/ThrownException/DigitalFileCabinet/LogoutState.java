/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class LogoutState implements DFCState {
    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }

    @Override
    public DFCState createAccount() {return null;}

    @Override
    public DFCState deleteAccount() {return null;}

    @Override
    public DFCState login(String email, String pwd) {return null;}

    @Override
    public DFCState openDoc() {return null;}

    @Override
    public DFCState saveDoc() {return null;}

    @Override
    public DFCState createDoc() {return null;}

    @Override
    public DFCState importDoc() {return null;}

    @Override
    public DFCState deleteDoc() {return null;}

    @Override
    public DFCState makeQuery() {
        return null;
    }

    @Override
    public DFCState browse() {return null;}

    @Override
    public DFCState logout() {
        // TODO : implementation goes here
        return this;
    }
}