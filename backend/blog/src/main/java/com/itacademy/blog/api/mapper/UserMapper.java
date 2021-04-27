package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.Role;
import com.itacademy.blog.model.User;
import com.itacademy.blog.services.DTO.RoleDTO;
import com.itacademy.blog.services.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO convert(User user);

    RoleDTO convert(Role value);
    Role convert(RoleDTO value);


    User convert(com.itacademy.blog.data.entity.User user);

    @Mapping(target = "password", constant = "********")
    User convert(UserDTO userDTO);

    @Mapping(target = "password", constant = "********")
    List<User> convert(List<UserDTO> users);
}
