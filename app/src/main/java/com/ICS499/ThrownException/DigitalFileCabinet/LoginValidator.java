package com.ICS499.ThrownException.DigitalFileCabinet;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator extends ViewModel {

    /* Validate password field */
    private boolean isPasswordValid(String password) {
        Pattern pattern1 = Pattern.compile("[\\W]");
        Pattern pattern2 = Pattern.compile("[0-9]");
        Pattern pattern3 = Pattern.compile("[a-zA-Z]");
        if(!password.isEmpty()){
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
        if(emailInput.isEmpty()) {
            return false;
        }
        else if(emailInput.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(emailInput).matches();
        } else {
            return false;
        }
    }

    public boolean validateEmailField(String input) {
        return isEmailValid(input);
    }

    public boolean validatePwdField(String input) {
        return isPasswordValid(input);
    }
}
