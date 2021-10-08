package com.homeproject.blog.backend.dtos;

public class Author {
    private String name;
    private String firstName;
    private String secondName;

    public Author(String name, String firstName, String secondName) {
        this.name = name;
        this.firstName = firstName;
        this.secondName = secondName;
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
