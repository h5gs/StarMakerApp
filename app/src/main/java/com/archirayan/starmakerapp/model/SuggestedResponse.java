package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by archirayan on 27/2/18.
 */

public class SuggestedResponse {
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

    public ArrayList<SuggestedName> getData() {
        return data;
    }

    public void setData(ArrayList<SuggestedName> data) {
        this.data = data;
    }

    @SerializedName("data")
    private ArrayList<SuggestedName> data;
}
