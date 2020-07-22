/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

/*
 * FileCabinet Class works as the context class for the state pattern
 */
public class FileCabinet {
    /* Instance variables */
    private static FileCabinet cabinet;
    private User dfcUser;
    private Document document;
    private DFCState state;

    /* The instances of each state the file cabinet can be in */
    private LoginState loginState;
    private CreateDocState createDocState;
    private LogoutState logoutState;
    private CreateAccountState createAccountState;
    private BrowseState browseState;
    private ImportDocState importDocState;
    private OpenDocState openDocState;
    private DeleteState deleteState;

    private FileCabinet(){}
    /* Singleton getInstance method */
    public FileCabinet getInstance(){
        if(cabinet == null){
            cabinet = new FileCabinet();
        }
        return cabinet;
    }

    public void createAccount(){
        /* Switch to create account state and carry out the task */
        try {
            createAccountState = new CreateAccountState(dfcUser);
            changeState(createAccountState);
            state.createAccount();
        } catch (Exception e) {
            // TODO: handle the exception here
            e.printStackTrace();
        }
    }
    public void deleteAccount(){
        /* Switch to delete state and carry out the task */
    }
    public void login(){
        /* Switch to login state and carry out the task */
    }
    public void logout(){
        /* Switch to logout state and carry out the task */
    }
    public void openDoc(){
        /* Switch to open document state and carry out the task */
    }
    public void saveDoc(){
        /* Switch to save document state and carry out the task */
    }
    public void importDoc(){
        /* Switch to import document state and carry out the task */
    }
    public void browse(){
        /* Switch to browse state and carry out the task */
    }
    public void changeState(DFCState newState){ state = newState;}

    public void isAuthenticate(){}

    /* set up a user for the digital file cabinet */
    public void setUser(User user){
        dfcUser = user;
    }
}
