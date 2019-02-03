package com.example.ravi.bikeservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    Thread SplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SplashThread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                Splash.this.finish();
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        };
        SplashThread.start();
    }

}
