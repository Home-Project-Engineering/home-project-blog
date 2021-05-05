package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {

    default UserDto toUserDto(UserEntity userEntity){
        if ( userEntity == null ) {
            return null;
        }

        var userDto = new UserDto();

        userDto.setId( userEntity.getId() );
        userDto.setName( userEntity.getName() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setEmail( userEntity.getEmail() );
        userDto.setPassword( userEntity.getPassword() );
        userDto.setRoleByte( userEntity.getRoleByte() );

        return setRole(userDto);
    }

    default Page<UserDto> toUserDtoPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    UserEntity toUserEntity(UserDto userDto);

    default UserDto setRole(UserDto userDto){
        var roleDto = new RoleDto();
        roleDto.setName(RoleDto.NameEnum.values()[(int)userDto.getRoleByte()]);
        userDto.setRole(roleDto);
        return userDto;
    }

}
