package com.softserveinc.ita.home.home_project_blog.controller.mapper;

import com.softserveinc.ita.home.home_project_blog.controller.dto.*;
import com.softserveinc.ita.home.home_project_blog.security.model.Role;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapperController {

    UserMapperController INSTANCE = Mappers.getMapper( UserMapperController.class );

    UserDto signUpToUserDto(CreateUserDto user);

    UserDto UpdateToUserDto(UpdateUserDto user);

    default RoleDto toRoleDto(Role role){
        return new RoleDto(role.toString().toLowerCase());
    }

    default Role toRole (RoleDto roleDto){
        return Role.valueOf(roleDto.getName().toUpperCase());
    }

    ViewUserDto toViewUserDto(UserDto user);

    ViewAuthorDto toViewAuthorDto(UserDto user);

    List<ViewUserDto> toViewUserDtos(List<UserDto> users);

    default Page<ViewUserDto> toPageViewUserDto(Page<UserDto> user) {
        return user.map(this::toViewUserDto);
    }
}
