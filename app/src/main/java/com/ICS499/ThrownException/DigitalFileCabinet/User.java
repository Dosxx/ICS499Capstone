/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import androidx.appcompat.app.AppCompatActivity;
import org.mindrot.jbcrypt.BCrypt;

/*
 * This is a singleton class
 */
public class User extends AppCompatActivity {
    private static User instance;
    private FileCabinet fileCabinet;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private QueryBuilder selectQuery;
    private QueryBuilder addQuery;
    private QueryContext sqlContext;

    /* Ensure only one instance of this class is created */
    private User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = hashPassword(password);
    }
    public User getUserInstance(String firstName, String lastName, String email, String password){
        if(instance == null){
            instance = new User(firstName, lastName, email, password);
        }
        return instance;
    }
    /* Definition of a method to hash and salt the password*/
    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(13));
    }
    /* Verify the password match */
    private boolean verifyHashPassword(String password, String hashPW){
        return BCrypt.checkpw(password, hashPW);
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
        return false;
    }

    public void makeQuery(){
        /* decide what query to make */
        sqlContext = new QueryContext();
        sqlContext.setQueryBuilder(addQuery);
        /*add user data into the database */
        addQuery = new AddUserQueryBuilder(getApplicationContext(), this);
        sqlContext.makeQuery();
        /*Select a user data from database*/
        selectQuery = new SelectUserQueryBuilder(getApplicationContext());
    }
}
