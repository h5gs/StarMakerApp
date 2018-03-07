package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by archirayan on 7/3/18.
 */

public class MySongsResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private ArrayList<MySongsList> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<MySongsList> getData() {
        return data;
    }

    public void setData(ArrayList<MySongsList> data) {
        this.data = data;
    }


}
