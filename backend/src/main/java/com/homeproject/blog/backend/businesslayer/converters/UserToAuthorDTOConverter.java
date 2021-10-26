package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.AuthorDTO;
import com.homeproject.blog.backend.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserToAuthorDTOConverter implements BlogDTOConverter<User, AuthorDTO> {
    
    @Override
    public AuthorDTO convert(User source) {
        return new AuthorDTO(source.getName(), source.getFirstName(), source.getLastName());
    }
}
