package com.homeproject.blog.backend.businesslayer.dto;

public class UserDTO {
    
    private String name;
    private String firstName;
    private String secondName;

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

    public UserDTO(String name, String firstName, String secondName) {
        this.name = name;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public UserDTO() {
    }
}
