/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public interface DFCState {
    /* Interfaces that the states need to implement individually */
    void setState(DFCContext context);
    public DFCState createAccount();
    public DFCState deleteAccount();
    public DFCState login();
    public DFCState logout();
    public DFCState openDoc();
    public DFCState saveDoc();
    public DFCState createDoc();
    public DFCState importDoc();
    public DFCState browse();
    public DFCState deleteDoc();
}
