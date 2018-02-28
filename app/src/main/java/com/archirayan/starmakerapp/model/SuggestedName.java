package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archirayan on 27/2/18.
 */

public class SuggestedName {

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @SerializedName("test")
    private String test;

}
