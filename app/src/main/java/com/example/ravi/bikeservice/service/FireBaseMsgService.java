package com.example.ravi.bikeservice.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ravi.bikeservice.NA;
import com.example.ravi.bikeservice.R;
import com.example.ravi.bikeservice.app.App;
import com.example.ravi.bikeservice.prefs.SharedPrefs;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseMsgService  extends FirebaseMessagingService {
    private static final String TAG = FireBaseMsgService.class.getSimpleName();
    private boolean isdriver;


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "onNewToken: "+s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "M_CH_ID");

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hearty365")
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Default notification")
                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .setContentInfo("Info");
        Log.d(TAG, "onMessageReceived: 1");

        if(SharedPrefs.getIsDriver()) {

            Log.d(TAG, "onMessageReceived: 2");
            Intent notificationIntent = new Intent(this, NA.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_ONE_SHOT);
            notificationBuilder.setContentIntent(contentIntent);



            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());
        }

    }

}
