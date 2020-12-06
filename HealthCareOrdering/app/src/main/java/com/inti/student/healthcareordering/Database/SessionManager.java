package com.inti.student.healthcareordering.Database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.inti.student.healthcareordering.LoginActivity;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidUserPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "username";
    // User name (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";
    // User name (make variable public to access from outside)
    public static final String KEY_CONTACT = "contact";

    // Constructor
    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session during login
     * */
    public void createLoginSession(String name, String pass, String contact){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASSWORD, pass);
        editor.putString(KEY_CONTACT, contact);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
//    public void checkLogin(){
//        // Check login status
//        if(!this.isLoggedIn()){
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(context, LoginActivity.class);
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            context.startActivity(i);
//        }
//
//    }

    /**
     * Get stored session data
     * */
    public String getUsername(){
        String username = pref.getString(KEY_NAME, null);

        return username;
    }

    public String getContact(){
        String contact = pref.getString(KEY_CONTACT, null);

        return contact;
    }
    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
