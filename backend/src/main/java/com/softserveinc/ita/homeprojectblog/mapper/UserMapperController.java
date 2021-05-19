package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.PasswordDto;
import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.model.Password;
import com.softserveinc.ita.homeprojectblog.model.Role;
import com.softserveinc.ita.homeprojectblog.model.User;
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

    @Mapping(target = "createOn", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    UserDto toUserDto(User body);

    Role toRole(RoleDto roleDto);

    PasswordDto toPasswordDto(Password password);

    @Mapping(target = "id", ignore = true)
    RoleDto toRoleDto(Role role);

}
