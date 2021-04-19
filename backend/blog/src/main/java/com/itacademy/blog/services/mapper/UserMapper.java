package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.services.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO convert(User user);
    User convert(UserDTO userDTO);
    List<UserDTO> convert(List<User> userEntities);
    default Page<UserDTO> convert(Page<User> userEntities){
        return userEntities.map(this::convert);
    }
}
