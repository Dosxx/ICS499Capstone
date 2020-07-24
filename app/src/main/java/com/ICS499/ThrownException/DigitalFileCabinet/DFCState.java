/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public interface DFCState {
    /* Interfaces that the states need to implement individually */
    void setState(DFCContext context);
    void createAccount();
    public void deleteAccount();
    public void login();
    public void logout();
    public void openDoc();
    public void saveDoc();
    public void createDoc();
    public void importDoc();
    public void browse();
    public void deleteDoc();
}
