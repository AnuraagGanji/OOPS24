package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue
    private Integer commentId;

    private String commentBody;

    private Integer userId;

    private Integer postId;

    public Comment(String commentBody, Integer userId, Integer postId) {
        this.commentBody = commentBody;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment() {

    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}
