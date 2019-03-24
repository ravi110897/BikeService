package com.example.ravi.bikeservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ravi.bikeservice.adapter.BikeListAdapter;
import com.example.ravi.bikeservice.pojo_modal.BikeListData;
import com.example.ravi.bikeservice.pojo_modal.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BikeListActivity extends AppCompatActivity {

    private static final String TAG = BikeListActivity.class.getSimpleName();
    private List<UserModel> userModels;
    private BikeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_list);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Registeration");

        myRef = myRef.child("Driver");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(adapter==null){
            adapter = new BikeListAdapter(userModels);
        }
        recyclerView.setAdapter(adapter);


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                userModels = new ArrayList<>();
                UserModel userModel = new UserModel();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.child("UserName").getValue() == null) {
                        userModel.setUserName("empty");
                    } else {
                        userModel.setUserName(dataSnapshot1.child("UserName").getValue().toString());
                    }

                    if (dataSnapshot1.child("Phone_No").getValue() == null) {
                        userModel.setPhoneNo("empty");
                    } else {
                        userModel.setPhoneNo(dataSnapshot1.child("Phone_No").getValue().toString());
                    }
                    userModels.add(userModel);
                    Log.d(TAG, "onDataChange: called ak 1:"+userModels.size());
                }
                adapter.setData(userModels);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        BikeListData[] myListData = new BikeListData[]{


                /*new BikeListData("Raj.", android.R.drawable.ic_dialog_email),
                new BikeListData("Rohit.", android.R.drawable.ic_dialog_info),
                new BikeListData("Adi.", android.R.drawable.ic_delete),
                new BikeListData("Ram.", android.R.drawable.ic_dialog_dialer),
                new BikeListData("Rohan.", android.R.drawable.ic_dialog_alert),
                new BikeListData("Yesh.", android.R.drawable.ic_dialog_map),
                new BikeListData("Deep.", android.R.drawable.ic_dialog_email),
                new BikeListData("Mass.", android.R.drawable.ic_dialog_info),
                new BikeListData("Khushal.", android.R.drawable.ic_delete),
                new BikeListData("Ajay.", android.R.drawable.ic_dialog_dialer),
                new BikeListData("Kevin.", android.R.drawable.ic_dialog_alert),
                new BikeListData("Shub.", android.R.drawable.ic_dialog_map),*/
        };
    }
}