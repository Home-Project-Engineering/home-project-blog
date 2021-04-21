package com.softserveinc.ita.homeprojectblog.controller.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserMapperController {
    UserMapperController INSTANCE = Mappers.getMapper(UserMapperController.class);

    @Mapping(target = "password", constant = "*****")
    User toUser(UserDto userDto);

    default Page<User> toUserPage(Page<UserDto> userDtoPage){
        return userDtoPage.map(this::toUser);
    }

//    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(User body);
}
