package com.homeproject.blog.backend.business.models.DTO;


import java.util.List;
import java.util.Objects;


public class PostDTO {
    private String text;
    private AuthorDTO authorDTO;
    private Long id;
    private List<TagDTO> tags;
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

    public List<TagDTO> getTags() {
        return (List<TagDTO>) tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostDTO(){}

    public PostDTO(Long id, List<TagDTO> tags, String createdOn, AuthorDTO authorDTO, String text, String title, String previewAttachment, String updatedOn) {
        this.id = id;
        this.tags = tags;
        this.createdOn = createdOn;
        this.authorDTO = authorDTO;
        this.text = text;
        this.title = title;
        this.previewAttachment = previewAttachment;
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(text, postDTO.text) &&
                Objects.equals(authorDTO, postDTO.authorDTO) &&
                Objects.equals(id, postDTO.id) &&
                Objects.equals(tags, postDTO.tags) &&
                Objects.equals(createdOn, postDTO.createdOn) &&
                Objects.equals(title, postDTO.title) &&
                Objects.equals(previewAttachment, postDTO.previewAttachment) &&
                Objects.equals(updatedOn, postDTO.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, authorDTO, id, tags, createdOn, title, previewAttachment, updatedOn);
    }
}