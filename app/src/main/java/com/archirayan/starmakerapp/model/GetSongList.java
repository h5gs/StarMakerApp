package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by archirayan on 28/2/18.
 */

public class GetSongList
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("imgae")
    @Expose
    private String imgae;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("hash_tag")
    @Expose
    private String hashTag;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gift")
    @Expose
    private String gift;
    @SerializedName("favorite")
    @Expose
    private String favorite;
    @SerializedName("like_status")
    @Expose
    private Integer likeStatus;
    @SerializedName("star")
    @Expose
    private Integer star;
    @SerializedName("top_gifter")
    @Expose
    private ArrayList<GetTOpgifters> topGifter = null;
    @SerializedName("likes")
    @Expose
    private Integer likes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public ArrayList<GetTOpgifters> getTopGifter() {
        return topGifter;
    }

    public void setTopGifter(ArrayList<GetTOpgifters> topGifter) {
        this.topGifter = topGifter;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    public class GetTOpgifters {
        @SerializedName("image")
        private String image;
        @SerializedName("id")
        private String id;
        @SerializedName("count")
        private String count;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
