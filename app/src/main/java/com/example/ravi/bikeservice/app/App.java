package com.example.ravi.bikeservice.app;

import android.app.Application;

public class App extends Application {

    private boolean isDriver;

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;


    }
    public static App getInstance(){
        return instance;
    }


}
