package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.presentationlayer.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserModelToUserDTOConverter implements BlogConverter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(source.getName());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setLastName(source.getLastName());
        userDTO.setEmail(source.getEmail());
        userDTO.setPassword(source.getPassword());
        return userDTO;
    }
}
