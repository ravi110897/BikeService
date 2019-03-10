package com.example.ravi.bikeservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ravi.bikeservice.adapter.BikeListAdapter;
import com.example.ravi.bikeservice.pojo_modal.BikeListData;

public class BikeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_list);



        BikeListData[] myListData = new BikeListData[] {
                new BikeListData("Raj.", android.R.drawable.ic_dialog_email),
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
                new BikeListData("Shub.", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        BikeListAdapter adapter = new BikeListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);




    }
}
