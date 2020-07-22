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
    public void createAccount() {/* This state is not responsible for this behavior */}

    @Override
    public void deleteAccount() {/* This state is not responsible for this behavior */}

    @Override
    public void login() {/* This state is not responsible for this behavior */}

    @Override
    public void logout() {/* This state is not responsible for this behavior */}

    @Override
    public void browse() {/* This state is not responsible for this behavior */}
    /* Method that apply to document are aggregated in this class*/

    @Override
    public void openDoc() {
        // TODO : implementation goes here
    }

    @Override
    public void saveDoc() {
        // TODO : implementation goes here
    }

    @Override
    public void createDoc() {
        // TODO: implementation goes here
    }

    @Override
    public void importDoc() {
        // TODO : implementation goes here
    }

    @Override
    public void deleteDoc() {
        // TODO : implementation goes here
    }
}
