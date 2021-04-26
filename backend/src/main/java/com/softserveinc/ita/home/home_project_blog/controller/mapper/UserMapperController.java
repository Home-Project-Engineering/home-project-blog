package com.softserveinc.ita.home.home_project_blog.controller.mapper;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.UpdateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewUserDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapperController {

    UserMapperController INSTANCE = Mappers.getMapper( UserMapperController.class );

    //@Mapping(target = "role", constant = ROLE.BLOGGER.name())
    UserDto signUpToUserDto(CreateUserDto user);

    //@Mapping(target = "role", constant = "BLOGGER")
    UserDto UpdateToUserDto(UpdateUserDto user);

    ViewUserDto toViewUserDto(UserDto user);

    List<ViewUserDto> toViewUserDtos(List<UserDto> users);

    default Page<ViewUserDto> toPageViewUserDto(Page<UserDto> user) {
        return user.map(this::toViewUserDto);
    }
}
