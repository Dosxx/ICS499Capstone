package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountModel extends ViewModel {
    private String first = null;
    private String last = null;
    private String email = null;
    private String pwd1 = null;
    private String pwd2 = null;
    private boolean isValid = false;

    /* Create account function*/
    public User createUser(Context context) {
            /* Create a user with valid input only */
            return User.getUserInstance(first, last, email, pwd1, context);
    }

    public String getFirst() {
        return first;
    }

    public String getEmail() {
        return email;
    }

    public String getLast() {
        return last;
    }

    public String getPwd1() {
        return pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

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
            } else if (matcher.find()) {
                return false;
            } else {
                return true;
            }
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
//        Pattern pattern1 = Pattern.compile("[\\W]");
//        Pattern pattern2 = Pattern.compile("[0-9]");
//        Pattern pattern3 = Pattern.compile("[a-zA-Z]");
//        if(password != null){
//            Matcher matcher1 = pattern1.matcher(password);
//            Matcher matcher2 = pattern2.matcher(password);
//            Matcher matcher3 = pattern3.matcher(password);
//            if(matcher1.find() && matcher2.find() && matcher3.find()
//                    && password.trim().length() >= 8){
//                return true;
//            }
//        }
        return password != null && password.trim().length() >= 8;
    }

    /* Verify the password match */
    private boolean verifyHashPassword(String password, String hashPW){
        return BCrypt.checkpw(password, hashPW);
    }

    /* Definition of a method to hash and salt the password*/
    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(13));
    }

    /* Validate input */
    public void inputValidation(final EditText fName, final EditText lName, final EditText emailInput, final EditText pass1, final EditText pass2){
        fName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!isNameValid(fName.getText().toString())) {
                        fName.setError("Not a valid email");
                    }
                    first = fName.getText().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        lName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!isNameValid(lName.getText().toString())) {
                        lName.setError("Not a valid Name");
                    }
                    last = lName.getText().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* Check that the email is a valid email*/
        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!isEmailValid(emailInput.getText().toString())) {
                        emailInput.setError("Not a valid email");
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
                        pass1.setError("Password must be 8 characters long");
                    }
                    pwd1 = hashPassword(pass1.getText().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /* check that both provided password match*/
        pass2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if(!pass1.getText().toString().equals(pass2.getText().toString())) {
                        pass2.setError("Password do not match");
                    }
                    pwd2 = pwd1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isValid() {
        if(first != null && last != null && email != null && pwd1 != null & pwd2 != null){
            isValid = true;
        }
        return isValid;
    }
}
