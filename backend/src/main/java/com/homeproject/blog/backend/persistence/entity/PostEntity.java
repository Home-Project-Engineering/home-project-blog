package com.homeproject.blog.backend.persistence.entity;

import com.homeproject.blog.backend.business.models.DTO.AuthorDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private Long id;
    private String title;
    private String text;
    private String createdOn;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private AuthorDTO authorDTO;
    @ManyToMany
    @JoinTable
    private List<TagEntity> tags;
    private String previewAttachment;
    private String updatedOn;

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
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

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
