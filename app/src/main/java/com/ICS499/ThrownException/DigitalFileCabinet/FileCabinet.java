/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

/*
 * FileCabinet Class works as the context class for the state pattern
 */
public class FileCabinet{
    private static FileCabinet cabinet;
    private DFCState state;
    private User user;
    private Document document;

    private FileCabinet(){}

    public FileCabinet getInstance(){
        if(cabinet == null){
            cabinet = new FileCabinet();
        }
        return cabinet;
    }

//    public void createAccount(){
//        /* Switch to create account state and carry out the task */
//    }
//    public void deleteAccount(){
//        /* Switch to delete state and carry out the task */
//    }
//    public void login(){
//        /* Switch to login state and carry out the task */
//    }
//    public void logout(){
//        /* Switch to logout state and carry out the task */
//    }
//    public void openDoc(){
//        /* Switch to open document state and carry out the task */
//    }
//    public void saveDoc(){
//        /* Switch to save document state and carry out the task */
//    }
//    public void importDoc(){
//        /* Switch to import document state and carry out the task */
//    }
//    public void browse(){
//        /* Switch to browse state and carry out the task */
//    }
//    public void setState(DFCState newState){ state = newState;}
//    public void isAuthenticate(){}

}
