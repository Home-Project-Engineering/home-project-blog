package com.softserveinc.ita.homeprojectblog.controller.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserMapperController {
    UserMapperController INSTANCE = Mappers.getMapper(UserMapperController.class);

    User toUser(UserDto userDto);
    default Page<User> toUserPage(Page<UserDto> userDtoPage){
        return userDtoPage.map(this::toUser);
    }

    UserDto toUserDto(User body);
}
