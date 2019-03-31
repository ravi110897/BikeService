package com.example.ravi.bikeservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ravi.bikeservice.prefs.SharedPrefs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registar extends AppCompatActivity {

    private Button registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);


        registration = (Button) findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Registar.this, Registration.class);
                startActivity(c);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (SharedPrefs.getFirstLogin()) {
            if (SharedPrefs.getIsDriver()) {
                Intent c = new Intent(Registar.this, DriverMapActivity.class);
                startActivity(c);

            } else {
                Intent c = new Intent(Registar.this, RiderMapsActivity.class);
                startActivity(c);

            }
        }

    }
}