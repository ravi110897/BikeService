package com.example.ravi.bikeservice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravi.bikeservice.R;
import com.example.ravi.bikeservice.pojo_modal.BikeListData;
import com.example.ravi.bikeservice.pojo_modal.UserModel;

import java.util.List;


public class BikeListAdapter extends RecyclerView.Adapter<BikeListAdapter.ViewHolder> {
    private static final String TAG = BikeListAdapter.class.getSimpleName();
    private List<UserModel> listdata;

    // RecyclerView recyclerView;
    public BikeListAdapter(List<UserModel> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.bikel_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(listdata.get(position).getUserName());
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "onDataChange: called ak 2:" + listdata);
        if (listdata == null) {
            return 0;
        } else {
            return listdata.size();
        }
    }

    public void setData(List<UserModel> userModels) {
        listdata = userModels;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}