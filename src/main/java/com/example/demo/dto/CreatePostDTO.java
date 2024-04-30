package com.example.demo.dto;

public class CreatePostDTO {
    private final String postBody;
    private final Integer userId;

    public CreatePostDTO(String postBody, Integer userId) {
        this.postBody = postBody;
        this.userId = userId;
    }

    public String getPostBody() {
        return postBody;
    }

    public Integer getUserId() {
        return userId;
    }
}
