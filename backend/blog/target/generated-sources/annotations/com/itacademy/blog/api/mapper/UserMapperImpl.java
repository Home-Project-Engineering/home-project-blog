package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.User;
import com.itacademy.blog.model.User.RoleEnum;
import com.itacademy.blog.services.DTO.UserDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-13T17:52:25+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO convert(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setRole( roleEnumToRoleEnum( user.getRole() ) );

        return userDTO;
    }

    @Override
    public User convert(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setFirstName( userDTO.getFirstName() );
        user.setLastName( userDTO.getLastName() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setRole( roleEnumToRoleEnum1( userDTO.getRole() ) );

        return user;
    }

    protected com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnumToRoleEnum(RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case GUEST: roleEnum1 = com.itacademy.blog.services.DTO.UserDTO.RoleEnum.GUEST;
            break;
            case USER: roleEnum1 = com.itacademy.blog.services.DTO.UserDTO.RoleEnum.USER;
            break;
            case MODERATOR: roleEnum1 = com.itacademy.blog.services.DTO.UserDTO.RoleEnum.MODERATOR;
            break;
            case ADMIN: roleEnum1 = com.itacademy.blog.services.DTO.UserDTO.RoleEnum.ADMIN;
            break;
            case EXPERT: roleEnum1 = com.itacademy.blog.services.DTO.UserDTO.RoleEnum.EXPERT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + roleEnum );
        }

        return roleEnum1;
    }

    protected RoleEnum roleEnumToRoleEnum1(com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case GUEST: roleEnum1 = RoleEnum.GUEST;
            break;
            case USER: roleEnum1 = RoleEnum.USER;
            break;
            case MODERATOR: roleEnum1 = RoleEnum.MODERATOR;
            break;
            case ADMIN: roleEnum1 = RoleEnum.ADMIN;
            break;
            case EXPERT: roleEnum1 = RoleEnum.EXPERT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + roleEnum );
        }

        return roleEnum1;
    }
}
