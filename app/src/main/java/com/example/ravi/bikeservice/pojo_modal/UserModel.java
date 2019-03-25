package com.example.ravi.bikeservice.pojo_modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("Phone_No")
    @Expose
    public String phoneNo;
    @SerializedName("UserName")
    @Expose
    public String userName;
    @SerializedName("Bike_NO")
    @Expose
    public String bikeNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBike_NO() {
        return bikeNo;
    }

    public void setBike_No(String bikeNo) {
        this.bikeNo = bikeNo;
    }



}
