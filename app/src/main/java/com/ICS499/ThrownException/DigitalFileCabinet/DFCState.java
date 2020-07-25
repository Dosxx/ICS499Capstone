/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public interface DFCState {
    /* Interfaces that the states need to implement individually */
    void setState(DFCContext context);
    DFCState createAccount();
    DFCState deleteAccount();
    DFCState login(String email, String password);
    DFCState logout();
    DFCState openDoc();
    DFCState saveDoc();
    DFCState createDoc();
    DFCState importDoc();
    DFCState browse();
    DFCState deleteDoc();
    DFCState makeQuery();
}
