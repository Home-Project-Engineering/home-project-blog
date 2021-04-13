package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.User;
import com.itacademy.blog.services.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO convert(User user);
    com.itacademy.blog.model.User convert(UserDTO userDTO);

}
