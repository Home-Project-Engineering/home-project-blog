package com.homeproject.blog.backend.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "role_types")
public class RoleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_type_generator")
    @SequenceGenerator(name = "role_type_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    String name;

    public RoleTypeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleTypeEntity() {
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
