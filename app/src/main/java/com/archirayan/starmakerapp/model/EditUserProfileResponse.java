package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by archirayan on 28/2/18.
 */

public class EditUserProfileResponse {
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

    public GetEditprofile getDate() {
        return date;
    }

    public void setDate(GetEditprofile date) {
        this.date = date;
    }

    @SerializedName("date")
    private GetEditprofile date;
}
