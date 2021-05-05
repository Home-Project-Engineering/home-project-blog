package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoGet;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoSet;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapperService {
    default UserEntity toUserEntity(UserDtoSet userDtoSet){
        if ( userDtoSet == null ) {
            return null;
        }
        // setRoleByte
        userDtoSet.setRoleByte((byte) userDtoSet.getRole().getName().ordinal());

        var userEntity = new UserEntity();

        userEntity.setId( userDtoSet.getId() );
        userEntity.setName( userDtoSet.getName() );
        userEntity.setFirstName( userDtoSet.getFirstName() );
        userEntity.setLastName( userDtoSet.getLastName() );
        userEntity.setEmail( userDtoSet.getEmail() );
        userEntity.setPassword( userDtoSet.getPassword() );
        userEntity.setCreateOn( userDtoSet.getCreateOn() );
        userEntity.setRoleByte( userDtoSet.getRoleByte() );

        return userEntity;
    }

    default Page<UserDtoGet> toUserDtoGetPage(Page<UserEntity> userEntityPage) {
        return userEntityPage.map(this::toUserDto);
    }

    default UserDtoGet toUserDto(UserEntity userEntity){
        if ( userEntity == null ) {
            return null;
        }

        var userDtoGet = new UserDtoGet();

        userDtoGet.setId( userEntity.getId() );
        userDtoGet.setName( userEntity.getName() );
        userDtoGet.setFirstName( userEntity.getFirstName() );
        userDtoGet.setLastName( userEntity.getLastName() );
        userDtoGet.setEmail( userEntity.getEmail() );
        userDtoGet.setPassword( userEntity.getPassword() );
        userDtoGet.setRoleByte( userEntity.getRoleByte() );

        return setRoleToUserDtoGet(userDtoGet);
    }

    default UserDtoGet setRoleToUserDtoGet(UserDtoGet userDtoGet){
        var roleDto = new RoleDto();
        roleDto.setName(RoleDto.NameEnum.values()[(int) userDtoGet.getRoleByte()]);
        userDtoGet.setRole(roleDto);
        return userDtoGet;
    }

}
