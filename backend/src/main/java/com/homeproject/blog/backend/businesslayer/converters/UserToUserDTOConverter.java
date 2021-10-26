package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOConverter implements BlogDTOConverter<User, UserDTO> {

    @Lazy
    @Autowired
    ConversionService conversionService;

    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(source.getId());
        userDTO.setName(source.getName());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setLastName(source.getLastName());
        userDTO.setEmail(source.getEmail());
        userDTO.setPassword(source.getPassword());
        userDTO.setRole(conversionService.convert(source.getRole(), RoleDTO.class));
        return userDTO;
    }
}
