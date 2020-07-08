package com.ICS499.ThrownException.DigitalFileCabinet;

public class LogoutState implements DFCState {
    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }
}
