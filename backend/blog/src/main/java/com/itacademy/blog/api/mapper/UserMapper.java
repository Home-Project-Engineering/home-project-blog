package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.User;
import com.itacademy.blog.services.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO convert(User user);
    User convert(com.itacademy.blog.data.entity.User user);
    @Mapping(target = "password", constant = "*****")
    User convert(UserDTO userDTO);

}
