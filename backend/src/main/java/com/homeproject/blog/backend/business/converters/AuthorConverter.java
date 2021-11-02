package com.homeproject.blog.backend.business.converters;

import com.homeproject.blog.backend.business.models.DTO.AuthorDTO;
import com.homeproject.blog.backend.business.models.DTO.RoleTypeDTO;
import com.homeproject.blog.backend.business.models.DTO.UserDTO;
import com.homeproject.blog.backend.persistence.entity.RoleTypeEntity;
import com.homeproject.blog.backend.persistence.entity.UserEntity;

public class AuthorConverter {
    public AuthorDTO entityToAuthor(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthorDTO(entity.getId(), entity.getName(), entity.getFirstName(), entity.getSecondName());
    }

    public UserDTO entityToUser(UserEntity entity) {
        RoleTypeDTO role = new RoleTypeDTO(entity.getRole().getId(), entity.getRole().getName());
        return new UserDTO(entity.getId(), entity.getName(), entity.getFirstName(), entity.getSecondName(), entity.getEmail(), entity.getPassword(), role);
    }

    public AuthorDTO authorToEntity(AuthorDTO author) {
        if (author == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(author.getId());
        entity.setName(author.getName());
        entity.setFirstName(author.getFirstName());
        entity.setSecondName(author.getLastName());
        return entity;
    }

    public UserEntity authorToEntity(UserDTO author) {
        if (author == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(author.getId());
        entity.setName(author.getName());
        entity.setFirstName(author.getFirstName());
        entity.setSecondName(author.getLastName());
        entity.setEmail(author.getEmail());
        entity.setPassword(author.getPassword());
        RoleTypeEntity role = new RoleTypeEntity();
        role.setId(author.getRole().getId());
        role.setName(author.getRole().getName());
        entity.setRole(role);
        return entity;
    }
}
