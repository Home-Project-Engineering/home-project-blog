package com.homeproject.blog.backend.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class TagEntity {
        @Id
        @GeneratedValue
        @Column(name = "id", nullable = false)
        private Long id;
        private String name;

        public TagEntity() {}

        public TagEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
