/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class BrowseState implements DFCState {

    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }

    @Override
    public DFCState createAccount() {return null;}

    @Override
    public DFCState deleteAccount() {return null;}

    @Override
    public DFCState login() {return null;}

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
    public DFCState browse() {
        // TODO implement browsing functionality here
        return this;
    }

    @Override
    public DFCState deleteDoc() {return null;}
}
