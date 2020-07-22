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
    public void createAccount() {
        /* Do Nothing here */
    }
}
