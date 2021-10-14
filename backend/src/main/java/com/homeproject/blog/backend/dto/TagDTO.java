package com.homeproject.blog.backend.dto;

import java.util.Objects;

public class TagDTO {
    Long postId;
    String text;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(postId, that.postId) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, text);
    }
}
