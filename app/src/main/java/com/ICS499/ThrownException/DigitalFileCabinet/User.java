/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.Application;
import android.content.Context;

import org.mindrot.jbcrypt.BCrypt;

/*
 * This is a singleton class
 */
public class User extends Application {
    private static User instance;
    private Context context;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private QueryBuilder selectQuery;
    private QueryBuilder addQuery;
    private QueryContext sqlContext;

    /* Ensure only one instance of this class is created */
    private User(String firstName, String lastName, String email, String password, Context appContext){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        context = appContext;
    }

    public static User getUserInstance(String firstName, String lastName, String email,
                                       String password, Context appContext){
        if(instance == null){
            instance = new User(firstName, lastName, email, password, appContext.getApplicationContext());
        }
        return instance;
    }

    /* Mutators and accessors*/
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isAuthenticate(String email, String password){
        // TODO: implement this method
        return false;
    }

    /* Verify the password match */
    //The password argument should not be hashed
    private boolean verifyHashPassword(String password, String hashPW){
        return BCrypt.checkpw(password, hashPW);
    }

    public void makeQuery(){
        /* decide what query to make */
//        sqlContext = new QueryContext();
//        sqlContext.setQueryBuilder(addQuery);
//        /*add user data into the database */
//        addQuery = new AddUserQueryBuilder(getApplicationContext(), this);
//        sqlContext.makeQuery();
//        /*Select a user data from database*/
//        selectQuery = new SelectUserQueryBuilder(getApplicationContext());
    }
}
