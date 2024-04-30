package com.example.demo.dto;

public class EditCommentDTO {
    private final String commentBody;
    private final Integer commentId;

    public EditCommentDTO(String commentBody, Integer commentId) {
        this.commentBody = commentBody;
        this.commentId = commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public Integer getCommentId() {
        return commentId;
    }
}
