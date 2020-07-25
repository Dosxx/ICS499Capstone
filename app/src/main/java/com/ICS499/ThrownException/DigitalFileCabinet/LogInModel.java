package com.ICS499.ThrownException.DigitalFileCabinet;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInModel extends ViewModel {
    private String email = null;
    private String pwd = null;
    private boolean isValid = false;
    public static final String PASSWORD_ERROR = "Incorrect Password";
    public static final String EMAIL_ERROR = "Not a valid email";


    /* Validate password field */
    private boolean isPasswordValid(String password) {
        Pattern pattern1 = Pattern.compile("[\\W]");
        Pattern pattern2 = Pattern.compile("[0-9]");
        Pattern pattern3 = Pattern.compile("[a-zA-Z]");
        if(password != null){
            Matcher matcher1 = pattern1.matcher(password);
            Matcher matcher2 = pattern2.matcher(password);
            Matcher matcher3 = pattern3.matcher(password);
            return matcher1.find() && matcher2.find()
                    && matcher3.find() && password.trim().length() >= 8;
        }else {
            return false;
        }
    }

    /* Validate email field */
    private boolean isEmailValid(String emailInput) {
        if(emailInput == null) {
            return false;
        }
        else if(emailInput.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(emailInput).matches();
        } else {
            return false;
        }
    }

    /* Validate input */
    public void inputValidation(final EditText emailInput, final EditText pass1){

        /* Check that the email is a valid email*/
        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!isEmailValid(emailInput.getText().toString())) {
                        emailInput.setError(EMAIL_ERROR);
                    }
                    email = emailInput.getText().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* check that the password is at least 8 characters long*/
        pass1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!isPasswordValid(pass1.getText().toString())) {
                        pass1.setError(PASSWORD_ERROR);
                    }
                    pwd = hashPassword(pass1.getText().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isValid() {
        if(email != null && pwd != null){
            isValid = true;
        }
        return isValid;
    }

    /* Definition of a method to hash and salt the password*/
    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(13));
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }
}
