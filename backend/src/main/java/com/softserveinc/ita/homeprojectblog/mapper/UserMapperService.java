package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import com.softserveinc.ita.homeprojectblog.model.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {

    UserEntity toUserEntity(UserDto userDto);

    default Page<UserDto> toUserDtoGetPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    UserDto toUserDto(UserEntity userEntity);

    User toUser(UserDto currentUserDto);
}
