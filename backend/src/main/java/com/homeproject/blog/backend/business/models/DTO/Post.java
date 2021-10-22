package com.homeproject.blog.backend.business.models.DTO;


import com.homeproject.blog.backend.business.models.Author;

import java.util.List;
import java.util.Objects;


public class Post {
    private String text;
    private Author author;
    private Long id;
    private List<Tag> tags;
    private String createdOn;
    private String title;
    private String previewAttachment;
    private String updatedOn;

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
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

    public List<Tag> getTags() {
        return (List<Tag>) tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post (){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(text, post.text) &&
                Objects.equals(author, post.author) &&
                Objects.equals(id, post.id) &&
                Objects.equals(tags, post.tags) &&
                Objects.equals(createdOn, post.createdOn) &&
                Objects.equals(title, post.title) &&
                Objects.equals(previewAttachment, post.previewAttachment) &&
                Objects.equals(updatedOn, post.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, author, id, tags, createdOn, title, previewAttachment, updatedOn);
    }
}