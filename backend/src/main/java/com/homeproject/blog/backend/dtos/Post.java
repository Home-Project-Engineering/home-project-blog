package com.homeproject.blog.backend.dtos;

import java.util.List;

public class Post {
    private Long id;
    private List<Tag> tags;
    private String createdOn;
    private Author author;
    private String text;
    private String title;
    private String previewAttachment;
    private String updatedOn;

    public Post() {}

    public Post(Long id, List<Tag> tags, String createdOn, Author author, String text, String title, String previewAttachment, String updatedOn) {
        this.id = id;
        this.tags = tags;
        this.createdOn = createdOn;
        this.author = author;
        this.text = text;
        this.title = title;
        this.previewAttachment = previewAttachment;
        this.updatedOn = updatedOn;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public boolean hasTag(Long tagId) {
        if (tags == null) {
            return false;
        }
        return tags.stream().anyMatch(tag -> tag.getId().equals(tagId));
    }

    public boolean hasTag(String tagName) {
        if (tags == null) {
            return false;
        }
        return tags.stream().anyMatch(tag -> tag.getName().equals(tagName));
    }
}
