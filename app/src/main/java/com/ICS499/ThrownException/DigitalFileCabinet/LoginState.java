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
    public void login() {
        // TODO : implementation goes here
    }

    @Override
    public void createAccount() {/* This state is not responsible for this behavior */}

    @Override
    public void deleteAccount() {/* This state is not responsible for this behavior */}

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
