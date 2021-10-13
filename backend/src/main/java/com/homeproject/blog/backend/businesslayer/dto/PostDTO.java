package com.homeproject.blog.backend.businesslayer.dto;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private Long id;
    private List<TagDTO> tags = new ArrayList<>();
    private UserDTO author;
    private String createdOn;
    private String text;
    private String title;
    private String previewAttachment;
    private String updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreviewAttachment() {
        return previewAttachment;
    }

    public void setPreviewAttachment(String previewAttachment) {
        this.previewAttachment = previewAttachment;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public PostDTO(Long id, List<TagDTO> tags, UserDTO author, String createdOn, String text, String title, String previewAttachment, String updatedOn) {
        this.id = id;
        this.tags = tags;
        this.author = author;
        this.createdOn = createdOn;
        this.text = text;
        this.title = title;
        this.previewAttachment = previewAttachment;
        this.updatedOn = updatedOn;
    }

    public PostDTO() {

    }
}
