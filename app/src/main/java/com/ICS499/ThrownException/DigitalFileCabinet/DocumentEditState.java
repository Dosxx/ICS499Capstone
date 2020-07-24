package com.ICS499.ThrownException.DigitalFileCabinet;

/*
 * Author: Thrown Exceptions
 * IVS499 Capstone 2020
 * Created: 7/22/2020
 */
class DocumentEditState implements DFCState {
    private Document document;
    private FileCabinet fileCabinet;

    @Override
    public void setState(DFCContext context) { }

    @Override
    public DFCState createAccount() {return null;}

    @Override
    public DFCState deleteAccount() {return null;}

    @Override
    public DFCState login() {return null;}

    @Override
    public DFCState logout() {return null;}

    @Override
    public DFCState browse() {return null;}
    /* Method that apply to document are aggregated in this class*/

    @Override
    public DFCState openDoc() {
        // TODO : implementation goes here
        return null;
    }

    @Override
    public DFCState saveDoc() {
        // TODO : implementation goes here
        return null;
    }

    @Override
    public DFCState createDoc() {
        // TODO: implementation goes here
        return null;
    }

    @Override
    public DFCState importDoc() {
        // TODO : implementation goes here
        return null;
    }

    @Override
    public DFCState deleteDoc() {
        // TODO : implementation goes here
        return null;
    }

    @Override
    public DFCState makeQuery() {
        return null;
    }
}
