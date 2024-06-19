package com.sm.syspago.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String KEY_IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String PREF_NAME = "SYSPAGO";
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean("señal",false);
    }
    public void setLogin(String username, String s,boolean señal) {
        editor.putString("username",username);
        editor.putString("password",s);
        editor.putBoolean("señal",señal);
        editor.commit();
    }

    public void cleancredentials(){
        editor.putString("username","");
        editor.putString("password","");
        editor.putBoolean("señal",false);
        // commit changes
        editor.commit();
    }

}