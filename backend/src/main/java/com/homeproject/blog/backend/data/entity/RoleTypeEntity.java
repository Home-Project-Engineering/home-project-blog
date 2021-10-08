package com.homeproject.blog.backend.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "role_types")
public class RoleTypeEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    String name;

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
