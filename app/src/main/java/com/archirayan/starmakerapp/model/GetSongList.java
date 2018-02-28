package com.archirayan.starmakerapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by archirayan on 28/2/18.
 */

public class GetSongList {
    @SerializedName("username")
    private String username;

    @SerializedName("profile_picture")
    private String profile_picture;

    @SerializedName("count")
    private String count;

    @SerializedName("hashtag")
    private String hashtag;

    @SerializedName("video")
    private String video;

    @SerializedName("stars")
    private String stars;

    @SerializedName("likes")
    private String likes;

    @SerializedName("top_gifters")
    private GetTOpgifters top_gifters;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public GetTOpgifters getTop_gifters() {
        return top_gifters;
    }

    public void setTop_gifters(GetTOpgifters top_gifters) {
        this.top_gifters = top_gifters;
    }



    private class GetTOpgifters {
        @SerializedName("user1")
        private String user1;

        @SerializedName("user2")
        private String user2;

        @SerializedName("user3")
        private String user3;

        public String getUser1() {
            return user1;
        }

        public void setUser1(String user1) {
            this.user1 = user1;
        }

        public String getUser2() {
            return user2;
        }

        public void setUser2(String user2) {
            this.user2 = user2;
        }

        public String getUser3() {
            return user3;
        }

        public void setUser3(String user3) {
            this.user3 = user3;
        }

    }
}
