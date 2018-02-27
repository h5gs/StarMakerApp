package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by archirayan on 27/2/18.
 */

public class Password implements Serializable
{

    @SerializedName("status")
    public String status;
    @SerializedName("msg")
    public String msg;
}