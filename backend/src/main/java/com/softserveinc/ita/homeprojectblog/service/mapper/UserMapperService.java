package com.softserveinc.ita.homeprojectblog.service.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {

    UserMapperService INSTANCE = Mappers.getMapper(UserMapperService.class);

    UserDto toUserDto(UserEntity userEntity);
    default Page<UserDto> toUserDtoPage(Page<UserEntity> userEntityPage){
        return userEntityPage.map(this::toUserDto);
    }

    UserEntity toUserEntity (UserDto userDto);
}
