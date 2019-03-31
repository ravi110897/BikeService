package com.example.ravi.bikeservice.prefs;

import android.content.SharedPreferences;

import com.example.ravi.bikeservice.app.App;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefs {

    private static final String MY_PREFS_NAME="prefs";
    private static SharedPreferences.Editor editor = App.getInstance().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();;
    private static SharedPreferences prefs  = App.getInstance().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);;
    private boolean isFirst;

    public static void setFirstLogin(boolean isLogin){
        editor.putBoolean("isLogin", isLogin);
        editor.apply();

    }
    public static boolean getFirstLogin(){
       return prefs.getBoolean("isLogin",false);

    }

    public static void setIsDriver(boolean isDriver)
    {
        editor.putBoolean("isDriver",isDriver);
    }
    public static boolean getIsDriver()
    {
        return prefs.getBoolean("isDriver",false);
    }

}
