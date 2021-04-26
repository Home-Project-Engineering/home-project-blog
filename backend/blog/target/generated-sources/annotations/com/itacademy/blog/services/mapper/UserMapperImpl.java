package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.data.entity.User.RoleEnum;
import com.itacademy.blog.services.DTO.UserDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-25T23:17:28+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO convert(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        if ( user.getId() != null ) {
            userDTO.setId( BigDecimal.valueOf( user.getId() ) );
        }
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

        if ( userDTO.getId() != null ) {
            user.setId( userDTO.getId().longValue() );
        }
        user.setName( userDTO.getName() );
        user.setFirstName( userDTO.getFirstName() );
        user.setLastName( userDTO.getLastName() );
        user.setEmail( userDTO.getEmail() );
        user.setRole( roleEnumToRoleEnum1( userDTO.getRole() ) );

        user.setPassword( "*****" );

        return user;
    }

    @Override
    public List<UserDTO> convert(List<User> userEntities) {
        if ( userEntities == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userEntities.size() );
        for ( User user : userEntities ) {
            list.add( convert( user ) );
        }

        return list;
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

    protected RoleEnum roleEnumToRoleEnum1(com.itacademy.blog.services.DTO.UserDTO.RoleEnum roleEnum) {
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
