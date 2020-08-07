/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.Application;

import java.io.Serializable;

/*
 * This is a singleton class
 */
public class User extends Application implements Serializable {
    private static User instance;
    private long user_id;  // set when user is added to the database
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /* Ensure only one instance of this class is created */
    private User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static User getUserInstance(String firstName, String lastName, String email,
                                       String password){
        if(instance == null){
            instance = new User(firstName, lastName, email, password);
        }
        return instance;
    }

    /* Mutators and accessors*/

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

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

    public String toString() {
        return String.format("First Name: %s\nLast Name: %s\nEmail address: %s",
                this.firstName.substring(0, 1).toUpperCase()+firstName.substring(1),
                this.lastName.substring(0, 1).toUpperCase()+lastName.substring(1),
                this.email);
    }
}
