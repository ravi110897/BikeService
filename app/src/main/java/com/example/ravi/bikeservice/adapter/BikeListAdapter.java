package com.example.ravi.bikeservice.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class BikeListAdapter extends RecyclerView.Adapter<BikeListAdapter.ViewHolder> {
    private static final String TAG = BikeListAdapter.class.getSimpleName();
    private List<UserModel> listdata;
    private  AlertDialog.Builder builder;
    private Context context;
    private static final String TOKEN = "cdRfD3cOR54:APA91bFzOtky7z5x1N5yUyaSJqLxnmMkRcm3HzCw10TKiQWSRoEKwjOd7bSWPgkVnO4UpqE9ZHJnPy9xQc-92olHnpH6O4-FSBudegLuOImoDB9WBkyBSavVYHYO5I4YHNc3groEHL0o";
    private static final String KEY_BODY = "body";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ICON = "icon";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NOTIFICATION = "notification";
    private static final String KEY_DATA = "data";
    private static final String KEY_REGEISTERATION_IDS = "registration_ids";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_FAILURE = "failure";
    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String MEDIA_TYPE = "application/json; charset=utf-8";
    private static final String SERVER_KEY = "key=AAAA2y_fGKI:APA91bETX8xCvNISVbYTEd6pSdeoUwLJ94Y33OuzFu-IqU-Zge4f_Yb2mJ7l10V9uZzZ6Gulqcac7GBTqHnha7MKUxrwPSVir6wus7617b4udjkJq5MOVXSVzRKFa9Wi3MgawJOjmyVk";
    private static final String AUTHORIZATION = "Authorization";
    private OkHttpClient mClient;

    // RecyclerView recyclerView;
    public BikeListAdapter(List<UserModel> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.bikel_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(listdata.get(position).getUserName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBox(position);
            }
        });
    }

    private void showDialogBox(int position){

        builder = new AlertDialog.Builder(context);
        builder.setMessage("\nName : "+listdata.get(position).getUserName()+"\n\nPhone_NO : "+listdata.get(position).getPhoneNo()+"\n\nBike_NO : "+listdata.get(position).getBike_NO()) .setTitle("Driver Data")
                .setCancelable(false)
                .setPositiveButton("Select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          sendNotification();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();


    }
        //sending notification from server using fcm(firebase cloud message)
    private void sendNotification() {

        Log.d(TAG, "sendNotification: 1");
        putDataTOJson();

    }

    private void putDataTOJson() {
        Log.d(TAG, "sendNotification: 2");
        mClient = new  OkHttpClient();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(TOKEN);
        sendMessage(jsonArray, "Hello", "How r u", "Http:\\google.com", "My Name is Vishal");
    }

    private void sendMessage(final JSONArray recipients, final String body, final String title, final String icon, final String message) {
        Log.d(TAG, "sendNotification: 3");
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    Log.d(TAG, "sendNotification: 4:"+s);
                    JSONObject resultJson = new JSONObject(s);
                    int success = resultJson.getInt(KEY_SUCCESS);
                    int failure = resultJson.getInt(KEY_FAILURE);
                    if (success == 1) {
                        showToastMessage("pass");
                    } else {
                        showToastMessage("fail");
                    }
                }catch (Exception JSONException) {
                    showToastMessage("Exception");
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Log.d(TAG, "sendNotification: 5");
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put(KEY_BODY, body);
                    notification.put(KEY_TITLE, title);
                    notification.put(KEY_ICON, icon);

                    JSONObject data = new JSONObject();
                    data.put(KEY_MESSAGE, message);
                    root.put(KEY_NOTIFICATION, notification);
                    root.put(KEY_DATA, data);
                    root.put(KEY_REGEISTERATION_IDS, recipients);

                   String result = postToFCM(root.toString());
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }

    private void showToastMessage(String pass) {

        Toast.makeText(context,pass, Toast.LENGTH_SHORT).show();
    }

    private String postToFCM(String s)throws IOException {
        MediaType JSON = MediaType.parse(MEDIA_TYPE);
        RequestBody body = RequestBody.create(JSON, s);
        Request request = new Request.Builder()
                .url(FCM_MESSAGE_URL)
                .post(body)
                .addHeader(AUTHORIZATION, SERVER_KEY)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();

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