package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archirayan on 9/3/18.
 */

public class MyFavoritesList
{

    @SerializedName("id")
    private String id;


    @SerializedName("user_id")
    private String user_id;

    @SerializedName("imgae")
    private String imgae;

    @SerializedName("video")
    private String video;

    @SerializedName("type")
    private String type;

    @SerializedName("caption")
    private String caption;

    @SerializedName("plays")
    private String plays;

    @SerializedName("post_id")
    private String post_id;

    @SerializedName("date")
    private String date;

    @SerializedName("favorites_id")
    private String favorites_id;





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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public String getPlays() {
        return plays;
    }

    public void setPlays(String plays) {
        this.plays = plays;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFavorites_id() {
        return favorites_id;
    }

    public void setFavorites_id(String favorites_id) {
        this.favorites_id = favorites_id;
    }

}
