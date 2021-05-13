package com.softserveinc.ita.home.home_project_blog.service.mapper;

import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import com.softserveinc.ita.home.home_project_blog.service.dto.AuthorDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {

    UserMapperService INSTANCE = Mappers.getMapper( UserMapperService.class );

    UserDto toUserDto(User user);

    AuthorDto toAuthorDto(User user);

    AuthorDto toAuthorDto(UserDto user);

    User toUser(UserDto user);

    default Page<UserDto> toPageUserDto(Page<User> all){
        return all.map(this::toUserDto);
    }

//    Page<UserDto> toPageUserDto(Page<User> all);
}
