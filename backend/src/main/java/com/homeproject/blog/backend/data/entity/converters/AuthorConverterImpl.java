package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.AuthorEntity;
import com.homeproject.blog.backend.dtos.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorConverterImpl implements AuthorConverter {

    @Override
    public Author entityToAuthor(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Author(entity.getName(), entity.getFirstName(), entity.getSecondName());
    }

    @Override
    public AuthorEntity authorToEntity(Author author) {
        if (author == null) {
            return null;
        }
        AuthorEntity entity = new AuthorEntity();
        entity.setName(author.getName());
        entity.setFirstName(author.getFirstName());
        entity.setSecondName(author.getSecondName());
        return entity;
    }
}
