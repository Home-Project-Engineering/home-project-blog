package com.softserveinc.ita.home.home_project_blog.mappers;

import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.models.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    //    @Mapping(target = "password", ignore = true)
    User signUpToUser(CreateUserDto user);

    List<UserDto> toUserDtos(List<User> users);

    default Optional<UserDto> toOptionalUserDto(Optional<User> user) {
        return user.map(this::toUserDto);
    }
}
