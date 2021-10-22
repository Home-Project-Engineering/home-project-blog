package com.homeproject.blog.backend.business.models.DTO;


import com.homeproject.blog.backend.business.models.Author;

import java.util.Objects;

public class Comment {
    private String text;
    private Long id;
    private Author author;
    private String createdOn;
    private String updatedOn;

    public Comment() {

    }

    public Comment(Post post, String text, Author author, Long id) {
        this.text = text;
        this.id = id;
        this.author = author;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text) &&
                Objects.equals(id, comment.id) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(createdOn, comment.createdOn) &&
                Objects.equals(updatedOn, comment.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, id, author, createdOn, updatedOn);
    }
}
