package com.example.demo.dto;

import com.example.demo.models.Comment;

import java.time.LocalDate;
import java.util.List;

public class PostDTO {
    private Integer postId;
    private String postBody;
    private LocalDate localDate;
    private List<CommentResponseDTO> comments;

    public PostDTO(Integer postId, String postBody, LocalDate localDate) {
        this.postId = postId;
        this.postBody = postBody;
        this.localDate = localDate;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public List<CommentResponseDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDTO> comments) {
        this.comments = comments;
    }
}
