package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by archirayan on 27/2/18.
 */

public class Signup implements Serializable
{
        @SerializedName("status")
        public String status;
        @SerializedName("message")
        public String msg;
}
