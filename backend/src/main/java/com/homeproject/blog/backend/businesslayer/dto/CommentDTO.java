package com.homeproject.blog.backend.businesslayer.dto;

import com.homeproject.blog.backend.data.entities.Post;

import java.time.OffsetDateTime;

public class CommentDTO {
    private Long id;

    private AuthorDTO author;

    private String text;

    private OffsetDateTime createdOn;

    private OffsetDateTime updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public OffsetDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CommentDTO(Long id, AuthorDTO author, String text, OffsetDateTime createdOn, OffsetDateTime updatedOn) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public CommentDTO() {
    }
}
