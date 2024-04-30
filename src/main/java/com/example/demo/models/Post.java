package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue
    private Integer postId;
    private String postBody;
    private LocalDate localDate = LocalDate.now();

    public Post(String postBody) {
        this.postBody = postBody;
    }

    public Post() {
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

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
