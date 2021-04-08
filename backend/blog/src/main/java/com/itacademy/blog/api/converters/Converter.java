package com.itacademy.blog.api.converters;

import com.itacademy.blog.model.User;
import com.itacademy.blog.repository.Entity.UserEntity;
import com.itacademy.blog.services.DTO.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    public UserDTO convert(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(UserDTO.RoleEnum.fromValue(user.getRole().getValue()));
        return userDTO;
    }
    public User convert(UserDTO userDTO){

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(User.RoleEnum.fromValue(userDTO.getRole().getValue()));
        return user;
    }
    public List<User> convert(List<UserDTO> userDTOList){
        List<User> userList = new ArrayList<>();
        for(UserDTO userDTO : userDTOList) {
            userList.add(convert(userDTO));
        }
        return userList;
    }

}
