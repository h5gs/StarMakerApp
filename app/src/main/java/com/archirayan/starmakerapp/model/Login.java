package com.archirayan.starmakerapp.model;

/**
 * Created by archirayan on 26/2/18.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Login implements Serializable
{

    @SerializedName("status")
    public String status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public data data;

    public class data implements Serializable
    {
        @SerializedName("id")
        public String user_id;
//        @SerializedName("user_firstname")
//        public String user_firstname;
//        @SerializedName("user_Lastname")
//        public String user_Lastname;
        @SerializedName("email_address")
        public String user_email;

    }
}