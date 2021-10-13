package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.RoleTypeEntity;
import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.dtos.Author;
import com.homeproject.blog.backend.dtos.RoleType;
import com.homeproject.blog.backend.dtos.User;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class AuthorConverterImpl implements AuthorConverter {

    @Override
    public Author entityToAuthor(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Author(entity.getId(), entity.getName(), entity.getFirstName(), entity.getSecondName());
    }

    @Override
    public User entityToUser(UserEntity entity) {
        RoleType role = new RoleType(entity.getRole().getId(), entity.getRole().getName());
        return new User(entity.getId(), entity.getName(), entity.getFirstName(), entity.getSecondName(), entity.getEmail(), entity.getPassword(), role);
    }

    @Override
    public UserEntity authorToEntity(Author author) {
        if (author == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(author.getId());
        entity.setName(author.getName());
        entity.setFirstName(author.getFirstName());
        entity.setSecondName(author.getSecondName());
        return entity;
    }

    @Override
    public UserEntity authorToEntity(User author) {
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
