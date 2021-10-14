package com.homeproject.blog.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="comment", nullable = false)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
