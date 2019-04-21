package com.example.ravi.bikeservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NA extends AppCompatActivity {

    private  AlertDialog.Builder builder;
    private TextView acceptedride;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        acceptedride= findViewById(R.id.acceptedride);

        showDialogBox();
    }

    private void showDialogBox(){

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Ready For Drive")
                .setCancelable(false)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        acceptedride.setText("You have accepted the ride.....!");

                    }
                })
                .setNegativeButton("Discart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        acceptedride.setText("You have discard the ride.....!");
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();


    }
}
