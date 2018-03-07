package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by archirayan on 7/3/18.
 */

public class NewSongListRespons {
    @SerializedName("status")
    private String status;

    @SerializedName("msg")
    private String msg;

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

    public ArrayList<NewSongList> getData() {
        return data;
    }

    public void setData(ArrayList<NewSongList> data) {
        this.data = data;
    }

    @SerializedName("data")
    private ArrayList<NewSongList> data;
}
