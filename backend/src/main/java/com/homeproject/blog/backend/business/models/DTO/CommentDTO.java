package com.homeproject.blog.backend.business.models.DTO;


import java.util.Objects;

public class CommentDTO {
    private String text;
    private Long id;
    private AuthorDTO authorDTO;
    private String createdOn;
    private String updatedOn;

    public CommentDTO() {

    }

    public CommentDTO(PostDTO postDTO, String text, AuthorDTO authorDTO, Long id) {
        this.text = text;
        this.id = id;
        this.authorDTO = authorDTO;
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

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO comment = (CommentDTO) o;
        return Objects.equals(text, comment.text) &&
                Objects.equals(id, comment.id) &&
                Objects.equals(authorDTO, comment.authorDTO) &&
                Objects.equals(createdOn, comment.createdOn) &&
                Objects.equals(updatedOn, comment.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, id, authorDTO, createdOn, updatedOn);
    }
}
