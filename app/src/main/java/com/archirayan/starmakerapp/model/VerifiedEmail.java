package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by archirayan on 27/2/18.
 */

public class VerifiedEmail implements Serializable {

    @SerializedName("status")
    public String status;
    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public Login.data data;

    public class data implements Serializable {
        @SerializedName("id")
        public String id;

        @SerializedName("email_address")
        public String email_address;

        @SerializedName("password")
        public String password;

        @SerializedName("social_token")
        public String social_token;

        @SerializedName("time")
        public String time;

        @SerializedName("verified_status")
        public String verified_status;

    }
}