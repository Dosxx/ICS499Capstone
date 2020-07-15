/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

/**
 * FileCabinet Class works as the context class for the state pattern
 */
public class FileCabinet {
    private static FileCabinet cabinet;

    private FileCabinet(){}

    public FileCabinet getInstance(){
        if(cabinet == null){
            cabinet = new FileCabinet();
        }
        return cabinet;
    }



}
