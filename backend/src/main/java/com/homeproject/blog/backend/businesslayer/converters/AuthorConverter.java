package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.dtos.Author;
import com.homeproject.blog.backend.dtos.User;

import java.util.List;

public interface AuthorConverter {

    Author entityToAuthor(UserEntity entity);

    User entityToUser(UserEntity entity);

    UserEntity authorToEntity(Author author);

    UserEntity authorToEntity(User author);
}
