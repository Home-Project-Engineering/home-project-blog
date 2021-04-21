package com.softserveinc.ita.homeprojectblog.controller.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapperController {
    UserMapperController INSTANCE = Mappers.getMapper(UserMapperController.class );

    User toUser(UserDto userDto);
    Page<User> toUserPage(Page<UserDto> userDtoPage);

    UserDto toUserDto(User body);
}
