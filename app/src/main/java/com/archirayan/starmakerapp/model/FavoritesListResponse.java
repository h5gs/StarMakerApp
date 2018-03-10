package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by archirayan on 9/3/18.
 */

public class FavoritesListResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private ArrayList<MyFavoritesList> data;

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

    public ArrayList<MyFavoritesList> getData() {
        return data;
    }

    public void setData(ArrayList<MyFavoritesList> data) {
        this.data = data;
    }


}
