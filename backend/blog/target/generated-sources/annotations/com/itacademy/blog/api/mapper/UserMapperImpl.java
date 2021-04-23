package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.User;
import com.itacademy.blog.model.User.RoleEnum;
import com.itacademy.blog.services.DTO.UserDTO;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-24T00:54:00+0300",
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
    public User convert(com.itacademy.blog.data.entity.User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        if ( user.getId() != null ) {
            user1.setId( BigDecimal.valueOf( user.getId() ) );
        }
        user1.setName( user.getName() );
        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setEmail( user.getEmail() );
        user1.setPassword( user.getPassword() );
        user1.setRole( roleEnumToRoleEnum1( user.getRole() ) );

        return user1;
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
        user.setRole( roleEnumToRoleEnum2( userDTO.getRole() ) );

        user.setPassword( "*****" );

        return user;
    }

    protected com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnumToRoleEnum(RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case BLOGGER: roleEnum1 = com.itacademy.blog.services.DTO.UserDTO.RoleEnum.BLOGGER;
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

    protected RoleEnum roleEnumToRoleEnum1(com.itacademy.blog.data.entity.User.RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case BLOGGER: roleEnum1 = RoleEnum.BLOGGER;
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

    protected RoleEnum roleEnumToRoleEnum2(com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case BLOGGER: roleEnum1 = RoleEnum.BLOGGER;
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
