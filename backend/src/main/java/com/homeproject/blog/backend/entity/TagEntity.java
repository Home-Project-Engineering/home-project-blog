package com.homeproject.blog.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TagEntity {
    @Id
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tagText;

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
