package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archirayan on 28/2/18.
 */

public class GetSongList {
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
    @SerializedName("title")
    private String title;
    @SerializedName("hash_tag")
    private String hash_tag;
    @SerializedName("status")
    private String status;
    @SerializedName("star")
    private String star;
    @SerializedName("likes")
    private String likes;



    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    @SerializedName("favorite")
    private String favorite;

//    @SerializedName("top_gifter")
//    private GetTOpgifters top_gifter;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

//    public GetTOpgifters getTop_gifter() {
//        return top_gifter;
//    }
//
//    public void setTop_gifter(GetTOpgifters top_gifter) {
//        this.top_gifter = top_gifter;
//    }


//    @SerializedName("username")
//    private String username;
//
//    @SerializedName("profile_picture")
//    private String profile_picture;
//
//    @SerializedName("count")
//    private String count;
//
//    @SerializedName("hashtag")
//    private String hashtag;
//
//    @SerializedName("video")
//    private String video;
//
//    @SerializedName("stars")
//    private String stars;
//
//    @SerializedName("likes")
//    private String likes;
//
//    @SerializedName("top_gifters")
//    private GetTOpgifters top_gifters;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getProfile_picture() {
//        return profile_picture;
//    }
//
//    public void setProfile_picture(String profile_picture) {
//        this.profile_picture = profile_picture;
//    }
//
//    public String getCount() {
//        return count;
//    }
//
//    public void setCount(String count) {
//        this.count = count;
//    }
//
//    public String getHashtag() {
//        return hashtag;
//    }
//
//    public void setHashtag(String hashtag) {
//        this.hashtag = hashtag;
//    }
//
//    public String getVideo() {
//        return video;
//    }
//
//    public void setVideo(String video) {
//        this.video = video;
//    }
//
//    public String getStars() {
//        return stars;
//    }
//
//    public void setStars(String stars) {
//        this.stars = stars;
//    }
//
//    public String getLikes() {
//        return likes;
//    }
//
//    public void setLikes(String likes) {
//        this.likes = likes;
//    }
//
//    public GetTOpgifters getTop_gifters() {
//        return top_gifters;
//    }
//
//    public void setTop_gifters(GetTOpgifters top_gifters) {
//        this.top_gifters = top_gifters;
//    }


//    private class GetTOpgifters {
//        @SerializedName("image")
//        private String image;
//        @SerializedName("id")
//        private String id;
//        @SerializedName("count")
//        private String count;
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getCount() {
//            return count;
//        }
//
//        public void setCount(String count) {
//            this.count = count;
//        }
//    }
}
