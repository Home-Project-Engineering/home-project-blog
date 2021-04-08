package com.itacademy.blog.services;

import com.itacademy.blog.repository.Entity.UserEntity;
import com.itacademy.blog.services.DTO.UserDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    public UserDTO convert(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(BigDecimal.valueOf(user.getId()));
        userDTO.setName(user.getName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(com.itacademy.blog.services.DTO.UserDTO.RoleEnum.fromValue(user.getRole().getValue()));
        return userDTO;
    }

    public List<UserDTO> convert(List<UserEntity> userEntityList){
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity user : userEntityList) {
            userDTOList.add(convert(user));
        }
        return userDTOList;
    }
}
