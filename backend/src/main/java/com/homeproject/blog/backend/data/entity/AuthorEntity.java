package com.homeproject.blog.backend.data.entity;

import com.homeproject.blog.backend.dtos.Author;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String firstName;
    private String secondName;

    public AuthorEntity() {}

    public AuthorEntity(Author author) {
        name = author.getName();
        firstName = author.getFirstName();
        secondName = author.getSecondName();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
