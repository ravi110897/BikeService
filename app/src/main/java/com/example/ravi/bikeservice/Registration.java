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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private static final String TAG = Registration.class.getSimpleName();
    private Switch switch1;
    //    private EditText ;
    private TextView tv1;
    private TextView tv;
    private boolean isdriver;
    private Button register;
    private FirebaseDatabase database;
    private DatabaseReference driverRef;
    private EditText editTextPhoneNo;
    private EditText editTextUserName;
    private DatabaseReference riderRef;
    private EditText editTextBikeNo;
    private EditText editTextLicense;
    private EditText editTextRc_Book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();

        database = FirebaseDatabase.getInstance();
        driverRef = database.getReference("Registeration").child("Driver");
        riderRef =  database.getReference("Registeration").child("Rider");

    }

    private void initViews() {

        switch1 = findViewById(R.id.switch1);
        editTextBikeNo = findViewById(R.id.editTextBikeNo);

        tv1 = findViewById(R.id.tv1);
        tv = findViewById(R.id.tv);
        register = findViewById(R.id.register);
        editTextPhoneNo = findViewById(R.id.editTextPhoneNo);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextBikeNo = findViewById(R.id.editTextBikeNo);
        editTextLicense = findViewById(R.id.editTextLicense);
        editTextRc_Book = findViewById(R.id.editTextRc_Book);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isdriver) {

                    DatabaseReference tempRef = null;

                    Log.d(TAG, "onClick: called:" + tempRef);

                    tempRef = driverRef.child(editTextPhoneNo.getText().toString());
                            /*.child("UserName").setValue(editTextUserName.getText().toString())
                            .child("Phone_No").setValue(editTextPhoneNo.getText().toString());*/


                    tempRef.child("Rc_Book  ").setValue(editTextRc_Book.getText().toString());
                    tempRef.child("License").setValue(editTextLicense.getText().toString());
                    tempRef.child("Bike_NO").setValue(editTextBikeNo.getText().toString());
                    tempRef.child("Phone_No").setValue(editTextPhoneNo.getText().toString());
                    tempRef.child("UserName").setValue(editTextUserName.getText().toString());








                    Toast.makeText(getBaseContext(), "Register as a Driver.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: 2");
                    Intent c = new Intent(Registration.this, DriverMapActivity.class);
                    startActivity(c);
                } else {

                    DatabaseReference tempRef = null;
//                    driverRef.child("Rider").push().setValue(1213);
                    tempRef = riderRef.child(editTextPhoneNo.getText().toString());
                            /*.child("UserName").setValue(editTextUserName.getText().toString())
                            .child("Phone_No").setValue(editTextPhoneNo.getText().toString());*/
                    tempRef.child("Phone_No").setValue(editTextPhoneNo.getText().toString());
                    tempRef.child("UserName").setValue(editTextUserName.getText().toString());


                    Toast.makeText(getBaseContext(), "Register as a Rider.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: 3");
                    Intent c = new Intent(Registration.this, RiderMapsActivity.class);
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
                    editTextBikeNo.setVisibility(View.VISIBLE);
                    editTextLicense.setVisibility(View.VISIBLE);
                    editTextRc_Book.setVisibility(View.VISIBLE);
                } else {
                    tv1.setText("Rider Registration");
                    tv.setText("Enable button to register for Driver....");
                    editTextBikeNo.setVisibility(View.GONE);
                    editTextLicense.setVisibility(View.GONE);
                    editTextRc_Book.setVisibility(View.GONE);
                }
            }

        });
    }


}
