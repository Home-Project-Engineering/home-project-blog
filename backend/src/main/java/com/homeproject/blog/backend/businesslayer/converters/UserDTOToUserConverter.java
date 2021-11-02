package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements BlogDTOConverter<UserDTO, User> {
    @Override
    public User convert(UserDTO source) {
        User user= new User();
        user.setName(source.getName());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        return user;
    }
}
