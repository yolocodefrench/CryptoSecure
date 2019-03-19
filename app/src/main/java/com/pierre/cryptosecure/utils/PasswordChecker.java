package com.pierre.cryptosecure.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Utilisateur on 19/03/2019.
 */

public class PasswordChecker {
    public static boolean isPasswordValid(String password){
        if( password.length() < 8 ){
            return false;
        }
        if(isAlphaNumeric(password)){
            return true;
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        Pattern p = Pattern.compile("[A-Za-z.0-9]*@[A-Za-z.0-9]*");
        Matcher m = p.matcher(email);
        boolean b =  m.matches();
        return b;
    }
    private static  boolean isAlphaNumeric(String password){

        Log.i("testPassword", "pass: "+password.matches("[a-z]*"));
        Log.i("testPassword", "passtest: "+password.matches("[a-z]*"));

        Pattern p = Pattern.compile("[a-z]");
        Matcher m = p.matcher(password);
        boolean b = m.find();

        Pattern p2 = Pattern.compile("[A-Z]");
        Matcher m2 = p2.matcher(password);
        boolean b2 =  m2.find();

        Pattern p3 = Pattern.compile("[0-9]");
        Matcher m3 = p3.matcher(password);
        boolean b3 =  m3.find();

        Log.i("testPassword", " b2:"+b2+" b3:"+b3);

        if( b && b2 && b3 ){
            return true;
        }
        return false;

    }
}
