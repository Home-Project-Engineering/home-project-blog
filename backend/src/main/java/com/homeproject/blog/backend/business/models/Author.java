package com.homeproject.blog.backend.business.models;

import java.util.Objects;

public class Author {
    private String name;
    private String firstName;
    private String lastName;

    public Author(){}

    public Author(String name, int id) {
        this.name = name;

    }

    public Author(Author author) {
        name=author.getName();
        firstName=author.getFirstName();
        lastName=author.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, lastName);
    }
}
