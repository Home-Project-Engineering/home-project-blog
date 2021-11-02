package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.presentationlayer.model.Role;
import com.homeproject.blog.backend.presentationlayer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserModelConverter implements BlogConverter<UserDTO, User> {

    @Lazy
    @Autowired
    ConversionService conversionService;

    @Override
    public User convert(UserDTO source) {
        User user = new User();
        user.setId(source.getId());
        user.setName(source.getName());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());
        user.setPassword(null);
        user.setRole(conversionService.convert(source.getRole(), Role.class));
        return user;
    }
}
