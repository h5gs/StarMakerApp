package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archirayan on 7/3/18.
 */

public class NewSongList {
    @SerializedName("id")
    private String id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("imgae")
    private String imgae;

    @SerializedName("type")
    private String type;

    @SerializedName("caption")
    private String caption;

    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImgae() {
        return imgae;
    }

    public void setImgae(String imgae) {
        this.imgae = imgae;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    @SerializedName("joined")
    private String joined;
}
