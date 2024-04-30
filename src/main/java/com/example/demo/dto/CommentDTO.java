package com.example.demo.dto;

public class CommentDTO {
    private final String commentBody;
    private final Integer postID;
    private final Integer userId;

    public CommentDTO(String commentBody, Integer postID, Integer userId) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userId = userId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public Integer getPostID() {
        return postID;
    }

    public Integer getUserID() {
        return userId;
    }
}
