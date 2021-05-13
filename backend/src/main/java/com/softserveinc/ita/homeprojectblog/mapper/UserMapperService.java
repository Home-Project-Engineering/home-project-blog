package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.RoleEntity;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {

    UserEntity toUserEntity(UserDto userDto);

    default Page<UserDto> toUserDtoPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    UserDto toUserDto(UserEntity userEntity);

    @Mapping(target = "id", source = "currentUserDto.id")
    @Mapping(target = "role", source = "currentUserDto.role")
    @Mapping(target = "createOn", source = "currentUserDto.createOn")
    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "name", source = "userDto.name")
    @Mapping(target = "firstName", source = "userDto.firstName")
    @Mapping(target = "lastName", source = "userDto.lastName")
    @Mapping(target = "password", source = "currentUserDto.password")
    @Mapping(target = "updatedOn", ignore = true)
    UserEntity toUserEntityFromUsersDto(UserDto currentUserDto, UserDto userDto);

    RoleDto toRoleDto(RoleEntity role);



    @Mapping(target = "id", source = "oldUserEntity.id")
    @Mapping(target = "role", source = "oldUserEntity.role")
    @Mapping(target = "createOn", source = "oldUserEntity.createOn")
    @Mapping(target = "email", source = "newUserEntity.email")
    @Mapping(target = "name", source = "newUserEntity.name")
    @Mapping(target = "firstName", source = "newUserEntity.firstName")
    @Mapping(target = "lastName", source = "newUserEntity.lastName")
    @Mapping(target = "password", source = "oldUserEntity.password")
    @Mapping(target = "updatedOn", ignore = true)
    UserEntity toUserEntityFromUsersEntity(UserEntity newUserEntity, UserEntity oldUserEntity);
}
