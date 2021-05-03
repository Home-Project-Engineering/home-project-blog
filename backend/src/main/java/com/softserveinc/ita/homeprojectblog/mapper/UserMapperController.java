package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperController {

    @Mapping(target = "password", constant = "*****")
    User toUser(UserDto userDto);

    default Page<User> toUserPage(Page<UserDto> userDtoPage) {
        return userDtoPage.map(this::toUser);
    }

    UserDto toUserDto(User body);
}
