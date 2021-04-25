package com.softserveinc.ita.homeprojectblog.service.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
@Validated
public interface UserMapperService {

    UserDto toUserDto(UserEntity userEntity);

    default Page<UserDto> toUserDtoPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    @Valid
    UserEntity toUserEntity(UserDto userDto);
}
