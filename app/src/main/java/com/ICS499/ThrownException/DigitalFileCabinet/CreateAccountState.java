package com.ICS499.ThrownException.DigitalFileCabinet;

public class CreateAccountState implements DFCState{
    CreateAccountState(){

    }

    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }
}
