package com.homeproject.blog.backend.businesslayer.dto;

import com.homeproject.blog.backend.data.entities.Post;

public class CommentDTO {
    private Long id;

    private String text;

    private String createdOn;

    private Post post;

    private String updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CommentDTO(Long id, String text, String createdOn, Post post, String updatedOn) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
        this.post = post;
        this.updatedOn = updatedOn;
    }

    public CommentDTO() {
    }
}
