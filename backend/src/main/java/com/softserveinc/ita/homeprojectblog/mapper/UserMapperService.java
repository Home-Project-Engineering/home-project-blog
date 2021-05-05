package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoGet;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoSet;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {
    UserEntity toUserEntity(UserDtoSet userDtoSet);

    default Page<UserDtoGet> toUserDtoPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    default UserDtoGet toUserDto(UserEntity userEntity){
        if ( userEntity == null ) {
            return null;
        }

        var userDto = new UserDtoGet();

        userDto.setId( userEntity.getId() );
        userDto.setName( userEntity.getName() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setEmail( userEntity.getEmail() );
        userDto.setPassword( userEntity.getPassword() );
        userDto.setRoleByte( userEntity.getRoleByte() );

        return setRole(userDto);
    }



    default UserDtoGet setRole(UserDtoGet userDtoGet){
        var roleDto = new RoleDto();
        roleDto.setName(RoleDto.NameEnum.values()[(int) userDtoGet.getRoleByte()]);
        userDtoGet.setRole(roleDto);
        return userDtoGet;
    }

}
