package com.teamnexters.volleytalk.follow;

/**
 * Created by MIN on 2017. 8. 2..
 */

public class TeamFollow {

    private String name;
    private String like;
    private String post;

    public TeamFollow(String name, String like, String post) {
        this.name = name;
        this.like = like;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
