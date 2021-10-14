package com.homeproject.blog.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostEntity {
    @Id
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="post", nullable = false)
    private String text;

    @Column(name = "autorId", nullable = false)
    private Long autorId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
