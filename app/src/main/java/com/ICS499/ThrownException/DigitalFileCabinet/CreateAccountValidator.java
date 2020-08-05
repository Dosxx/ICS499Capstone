package com.ICS499.ThrownException.DigitalFileCabinet;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountValidator extends ViewModel {

    /* validation name field */
    private boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        if(name == null){
            return false;
        }else {
            Matcher matcher = pattern.matcher(name);
            if (name.length() == 0) {
                return false;
            } else if (name.length() > 20) {
                return false;
            } else return !matcher.find();
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

    /* Definition of a method to hash and salt the password*/
    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(13));
    }

    public boolean validateNameField(String input) {
        return isNameValid(input);
    }

    public boolean validateEmailField(String input) {
        return isEmailValid(input);
    }

    public boolean validatePwdField(String input) {
        return isPasswordValid(input);
    }
}
