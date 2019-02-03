package com.example.ravi.bikeservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private static final String TAG = Registration.class.getSimpleName();
    private Switch switch1;
    private EditText bikeno;
    private EditText license;
    private EditText rc_book;
    //    private EditText ;
    private TextView tv1;
    private TextView tv;
    private boolean isdriver;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();
    }

    private void initViews() {

        switch1 = findViewById(R.id.switch1);
        bikeno = findViewById(R.id.bikeno);
        license = findViewById(R.id.license);
        rc_book = findViewById(R.id.rc_book);
        tv1 = findViewById(R.id.tv1);
        tv = findViewById(R.id.tv);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isdriver) {
                    Log.d(TAG, "onClick: 2");
                    Intent c = new Intent(Registration.this, MapsActivity.class);
                    startActivity(c);
                } else {
                    Log.d(TAG, "onClick: 3");
                    Intent c = new Intent(Registration.this, MapsActivity.class);
                    startActivity(c);
                }

            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: ");
                isdriver = isChecked;

                if (isChecked) {

                    tv1.setText("Driver Registration");
                    tv.setText("Disable button to register for Rider....");
                    bikeno.setVisibility(View.VISIBLE);
                    license.setVisibility(View.VISIBLE);
                    rc_book.setVisibility(View.VISIBLE);
                } else {
                    tv1.setText("Rider Registration");
                    tv.setText("Enable button to register for Driver....");
                    bikeno.setVisibility(View.GONE);
                    license.setVisibility(View.GONE);
                    rc_book.setVisibility(View.GONE);
                }
            }

        });
    }


}
