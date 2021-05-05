package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDtoGet;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoSet;
import com.softserveinc.ita.homeprojectblog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperController {

    @Mapping(target = "password", constant = "*****")
    User toUser(UserDtoGet userDtoGet);

    default Page<User> toUserPage(Page<UserDtoGet> userDtoPage) {
        return userDtoPage.map(this::toUser);
    }

    UserDtoSet toUserDto(User body);
}
