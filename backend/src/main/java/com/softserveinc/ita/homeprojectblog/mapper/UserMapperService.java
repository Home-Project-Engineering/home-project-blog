package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.UserDtoGet;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoSet;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {

    UserEntity toUserEntity(UserDtoSet userDtoSet);

    default Page<UserDtoGet> toUserDtoGetPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    UserDtoGet toUserDto(UserEntity userEntity);

}
