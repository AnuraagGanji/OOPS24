package com.example.demo.dto;

public class CommentResponseDTO {
    private Integer commentId;
    private String commentBody;
    private CommentCreator commentCreator;

    public Integer getCommentId() {
        return commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setCommentCreator(Integer userId, String name) {
        this.commentCreator = new CommentCreator(userId, name);
    }
}

class CommentCreator {
    private Integer userId;
    private String name;

    public CommentCreator(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
