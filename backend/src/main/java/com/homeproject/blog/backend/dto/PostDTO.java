package com.homeproject.blog.backend.dto;

import java.util.Objects;

public class PostDTO {
    private Integer authorId;
    private String text;

    @Override
    public int hashCode() {
        return Objects.hash(authorId, text);
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(authorId, postDTO.authorId) && Objects.equals(text, postDTO.text);
    }
}
