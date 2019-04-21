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

import com.example.ravi.bikeservice.app.App;
import com.example.ravi.bikeservice.prefs.SharedPrefs;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        riderRef = database.getReference("Registeration").child("Rider");

        //phone number validation

//        public void validatePhone (View view){
//            String phone = editTextPhoneNo.getText().toString();
//            if (isValidPhone(phone)) {
//                Toast.makeText(view.getContext(), "Phone number is valid", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(view.getContext(), "Phone number is invalid", Toast.LENGTH_LONG).show();
//            }
//        }


    }

    private boolean isValidPhone(String phone) {

        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
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

                final String etUserName = editTextUserName.getText().toString();
                final String etPhoneNo = editTextPhoneNo.getText().toString();
                final String etBikeNo = editTextBikeNo.getText().toString();
                final String etLicense = editTextLicense.getText().toString();
                final String etRc_Book = editTextRc_Book.getText().toString();




                if (isdriver) {

                    //VALADATION
                    if(etUserName.length()==0)

                    {
                        editTextUserName.requestFocus();
                        editTextUserName.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(!etUserName.matches("[a-zA-Z ]+"))
                    {
                        editTextUserName.requestFocus();
                        editTextUserName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }

                    else if(etPhoneNo.length()==0)
                    {
                        editTextPhoneNo.requestFocus();
                        editTextPhoneNo.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(!etPhoneNo.matches("\\d{10}"))
                    {
                        editTextPhoneNo.requestFocus();
                        editTextPhoneNo.setError("ENTER THE PROPER NUMBER");
                    }
                   else if(etBikeNo.length()==0)
                    {
                        editTextBikeNo.requestFocus();
                        editTextBikeNo.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(etLicense.length()==0)
                    {
                        editTextLicense.requestFocus();
                        editTextLicense.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(etRc_Book.length()==0)
                    {
                        editTextRc_Book.requestFocus();
                        editTextRc_Book.setError("FIELD CANNOT BE EMPTY");
                    }
                    else {
                        Toast.makeText(Registration.this, "Validation Successful", Toast.LENGTH_LONG).show();

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
                        SharedPrefs.setFirstLogin(true);
                        SharedPrefs.setIsDriver(true);
                        Intent c = new Intent(Registration.this, DriverMapActivity.class);
                        startActivity(c);
                    }

                } else {
                        //VALADATION
                    if(etUserName.length()==0)

                    {
                        editTextUserName.requestFocus();
                        editTextUserName.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(!etUserName.matches("[a-zA-Z ]+"))
                    {
                        editTextUserName.requestFocus();
                        editTextUserName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }

                    else if(etPhoneNo.length()==0)
                    {
                        editTextPhoneNo.requestFocus();
                        editTextPhoneNo.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(!etPhoneNo.matches("\\d{10}"))
                    {
                        editTextPhoneNo.requestFocus();
                        editTextPhoneNo.setError("ENTER THE PROPER NUMBER");
                    }
                else
                    {


                        Toast.makeText(Registration.this,"Validation Successful",Toast.LENGTH_LONG).show();
                        DatabaseReference tempRef = null;
//                    driverRef.child("Rider").push().setValue(1213);
                        tempRef = riderRef.child(editTextPhoneNo.getText().toString());
                            /*.child("UserName").setValue(editTextUserName.getText().toString())
                            .child("Phone_No").setValue(editTextPhoneNo.getText().toString());*/
                        tempRef.child("Phone_No").setValue(editTextPhoneNo.getText().toString());
                        tempRef.child("UserName").setValue(editTextUserName.getText().toString());

                        SharedPrefs.setFirstLogin(true);

                        Toast.makeText(getBaseContext(), "Register as a Rider.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onClick: 3");
                        Intent c = new Intent(Registration.this, RiderMapsActivity.class);
                        startActivity(c);
                    }

                }

            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: ");
                isdriver = isChecked;

                if (isChecked) {
                    App.getInstance().setDriver(isChecked);
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
