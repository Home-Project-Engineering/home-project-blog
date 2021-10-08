package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.AuthorEntity;
import com.homeproject.blog.backend.dtos.Author;

public interface AuthorConverter {

    Author entityToAuthor(AuthorEntity entity);

    AuthorEntity authorToEntity(Author author);
}
