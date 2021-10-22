package com.homeproject.blog.backend.persistence.entity;

import com.homeproject.blog.backend.business.models.Author;

import javax.persistence.*;

@Table(name = "comment")
@Entity
public class CommentEntity {
        @Id
        @GeneratedValue
        @Column(name = "id", nullable = false)
        private Long id;
        private String text;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn
        private Author author;
        private String createdOn;
        private String updatedOn;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
}
