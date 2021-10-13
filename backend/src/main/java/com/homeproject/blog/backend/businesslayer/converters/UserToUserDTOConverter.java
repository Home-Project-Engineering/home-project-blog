package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(source.getName());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setSecondName(source.getSecondName());
        return userDTO;
    }
}
