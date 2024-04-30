package com.example.demo.dto;

public class EditPostDTO {
    private final String postBody;
    private final Integer postId;

    public EditPostDTO(String postBody, Integer postId) {
        this.postBody = postBody;
        this.postId = postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public Integer getPostId() {
        return postId;
    }
}
