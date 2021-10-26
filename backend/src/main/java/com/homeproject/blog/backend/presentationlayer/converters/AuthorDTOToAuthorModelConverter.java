package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.AuthorDTO;
import com.homeproject.blog.backend.presentationlayer.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDTOToAuthorModelConverter implements BlogConverter<AuthorDTO, Author> {

    @Override
    public Author convert(AuthorDTO source) {
        Author author = new Author();
        author.setName(source.getName());
        author.setFirstName(source.getFirstName());
        author.setLastName(source.getLastName());
        return author;
    }
}
