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
    private Context context;

    /* The instances of each state the file cabinet can be in */

    private EditAccount editAccount;
    private EditDocument editDocument;
    private FileBrowser fileBrowser;

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

    /* set up a user for the digital file cabinet */
    public void setUser(User user) {
        dfcUser = user;
    }
    public User getUser() {
        return dfcUser;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public void setEditAccount(EditAccount editAccount) {
        this.editAccount = editAccount;
    }
    public EditAccount getEditAccount() {
        return editAccount;
    }
}
