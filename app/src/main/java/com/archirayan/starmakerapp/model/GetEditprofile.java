package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archirayan on 28/2/18.
 */

public class GetEditprofile{
        @SerializedName("id")
        private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
        private String image;
}
