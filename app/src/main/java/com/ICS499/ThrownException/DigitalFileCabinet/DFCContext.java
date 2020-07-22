/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class DFCContext {
    private DFCState state;

    public DFCContext(){
        state = null;
    }

    public void setState(DFCState state){
        this.state = state;
    }

    public DFCState getState(){
        return state;
    }

}
