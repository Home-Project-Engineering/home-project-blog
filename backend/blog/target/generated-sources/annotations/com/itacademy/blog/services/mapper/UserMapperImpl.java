package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.data.entity.User.RoleEnum;
import com.itacademy.blog.data.entity.User.UserBuilder;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.DTO.UserDTO.UserDTOBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-16T22:02:59+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO convert(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        if ( user.getId() != null ) {
            userDTO.id( BigDecimal.valueOf( user.getId() ) );
        }
        userDTO.name( user.getName() );
        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.role( roleEnumToRoleEnum( user.getRole() ) );

        return userDTO.build();
    }

    @Override
    public User convert(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        if ( userDTO.getId() != null ) {
            user.id( userDTO.getId().longValue() );
        }
        user.name( userDTO.getName() );
        user.firstName( userDTO.getFirstName() );
        user.lastName( userDTO.getLastName() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );
        user.role( roleEnumToRoleEnum1( userDTO.getRole() ) );

        return user.build();
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
