package com.example;

import java.sql.Timestamp;
import java.util.List;

public class ForumPost {
    private int postId;
    private int userId;
    private String postTitle;
    private String postContent;
    private Timestamp createdAt;
    private String username;
    private List<ForumReply> replies;

    // Getters and setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ForumReply> getReplies() {
        return replies;
    }

    public void setReplies(List<ForumReply> replies) {
        this.replies = replies;
    }
}
