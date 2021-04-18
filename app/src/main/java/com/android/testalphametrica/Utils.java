package com.android.testalphametrica;

import android.util.Log;

import java.util.regex.Pattern;

public class Utils {

    public static String CLASS_TAG=Utils.class.getSimpleName();
    public static String USERNAME="test@luxpmsoft.com";
    public static String PASSWORD="test1234!";

    public static boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }

    public static boolean isValidPassAndConfirm(String pass, String c_pass) {
        if(pass.equals(c_pass)) {
            return true;
        }
        return false;
    }


    public static boolean checkStringCapital(String str) {
        char ch;
        boolean capitalFlag = false;

        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
            Log.e(CLASS_TAG,"checkStringCapital "+capitalFlag);

        }
        return capitalFlag;
    }

    public static boolean checkStringHasNumber(String str) {
        char ch;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
        }
        return numberFlag;
    }

    public static boolean checkStringHasSpecialCharacter(String str) {

    return str.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

    }


}
