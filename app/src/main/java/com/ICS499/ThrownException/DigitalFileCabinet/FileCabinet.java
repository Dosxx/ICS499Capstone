/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.Application;
import android.content.Context;

/*
 * FileCabinet Class works as the context class for the state pattern
 */
public class FileCabinet extends Application {
    /* Instance variables */
    private static FileCabinet cabinet;
    private User dfcUser;
    private Document document;
    private DFCState state;
    private Context context;

    /* The instances of each state the file cabinet can be in */
    private AccountState accountState;
    private EditDocument documentEditState;
    private FileBrowser browseState;

    private FileCabinet(Context context) {
        this.context = context.getApplicationContext();
    }

    /* Singleton getInstance method */
    public static FileCabinet getInstance(Context context) {
        if(cabinet == null){
            cabinet = new FileCabinet(context);
        }
        return cabinet;
    }

    public DFCState createAccount() {
        /* Switch to the account state and carry out the task */
        if (accountState == null) {
            /* Instantiate in null */
            accountState = new AccountState(dfcUser);
        }
        changeState(accountState);
        state.createAccount();
        return state;

    }
    public void deleteAccount() {
        /* Switch to the account state and carry out the task */
        try {
            if (accountState == null) {
                /* Instantiate in null */
                accountState = new AccountState(dfcUser);
            }
            changeState(accountState);
            state.deleteAccount();
        } catch (Exception e) {
            //TODO : handle the exception here
            e.printStackTrace();
        }
    }
    public void login(String email, String pwd) {
    }
    public void logout() {
        /* Switch to logout state and carry out the task */
        try {

        } catch (Exception e) {
            // TODO: handle the exception here
            e.printStackTrace();
        }
    }
    public void openDoc() {
        /* Switch to document edit state and carry out the task */
        try{
            //TODO: instantiate EditDocument
        }catch (Exception e){
            //TODO : handle the exception properly here
            e.printStackTrace();
        }
    }
    public void saveDoc() {
        /* Switch to document edit state and carry out the task */
        try{

        }catch (Exception e){
            //TODO : handle the exception properly here
            e.printStackTrace();
        }
    }
    public void importDoc() {
        /* Switch to document edit state and carry out the task */
        try{

        }catch (Exception e){
            //TODO : handle the exception properly here
            e.printStackTrace();
        }
    }
    public void deleteDoc() {
        /* Switch to document edit state to carry out the task */
        try{

        }catch (Exception e){
            //TODO : handle the exception properly here
            e.printStackTrace();
        }
    }
    public void browse() {
        /* Switch to browse state and carry out the task */
        try {
            if (browseState == null) {
                /* Instantiate in null */
                browseState = new FileBrowser();
            }
            changeState(browseState);
            state.browse();
        } catch (Exception e) {
            // TODO: handle the exception here
            e.printStackTrace();
        }
    }
    public void changeState(DFCState newState){ state = newState;}

    public void isAuthenticate() {}

    /* set up a user for the digital file cabinet */
    public void setUser(User user) {
        dfcUser = user;
    }
    public User getUser() {
        return dfcUser;
    }
}
