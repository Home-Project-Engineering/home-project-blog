package com.softserveinc.ita.home.blog.services.mapper;

import com.softserveinc.ita.home.blog.model.User;
import com.softserveinc.ita.home.blog.services.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO convert(User user);
    User convert(UserDTO userDTO);
}
