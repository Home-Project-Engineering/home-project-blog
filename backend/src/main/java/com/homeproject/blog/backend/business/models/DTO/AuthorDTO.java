package com.homeproject.blog.backend.business.models.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class AuthorDTO {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorDTO(){}


    public AuthorDTO(AuthorDTO authorDTO) {
        name= authorDTO.getName();
        firstName= authorDTO.getFirstName();
        lastName= authorDTO.getLastName();
    }

    public AuthorDTO(Long id, String name, String firstName, String secondName) {
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
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(name, authorDTO.name) &&
                Objects.equals(firstName, authorDTO.firstName) &&
                Objects.equals(lastName, authorDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, lastName);
    }
}
