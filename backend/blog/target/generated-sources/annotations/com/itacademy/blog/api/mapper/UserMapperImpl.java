package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.Role;
import com.itacademy.blog.model.Role.RoleEnum;
import com.itacademy.blog.model.User;
import com.itacademy.blog.services.DTO.RoleDTO;
import com.itacademy.blog.services.DTO.RoleDTO.RoleDTOBuilder;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.DTO.UserDTO.UserDTOBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-27T11:03:38+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO convert(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.name( user.getName() );
        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.role( convert( user.getRole() ) );

        return userDTO.build();
    }

    @Override
    public RoleDTO convert(Role value) {
        if ( value == null ) {
            return null;
        }

        RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.role( roleEnumToRoleEnum( value.getRole() ) );

        return roleDTO.build();
    }

    @Override
    public Role convert(RoleDTO value) {
        if ( value == null ) {
            return null;
        }

        Role role = new Role();

        role.setRole( roleEnumToRoleEnum1( value.getRole() ) );

        return role;
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
        user1.setRole( roleToRole( user.getRole() ) );

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
        user.setRole( convert( userDTO.getRole() ) );

        user.setPassword( "********" );

        return user;
    }

    @Override
    public List<User> convert(List<UserDTO> users) {
        if ( users == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( users.size() );
        for ( UserDTO userDTO : users ) {
            list.add( convert( userDTO ) );
        }

        return list;
    }

    protected com.itacademy.blog.services.DTO.RoleDTO.RoleEnum roleEnumToRoleEnum(RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        com.itacademy.blog.services.DTO.RoleDTO.RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case BLOGGER: roleEnum1 = com.itacademy.blog.services.DTO.RoleDTO.RoleEnum.BLOGGER;
            break;
            case ADMIN: roleEnum1 = com.itacademy.blog.services.DTO.RoleDTO.RoleEnum.ADMIN;
            break;
            case MODERATOR: roleEnum1 = com.itacademy.blog.services.DTO.RoleDTO.RoleEnum.MODERATOR;
            break;
            case EXPERT: roleEnum1 = com.itacademy.blog.services.DTO.RoleDTO.RoleEnum.EXPERT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + roleEnum );
        }

        return roleEnum1;
    }

    protected RoleEnum roleEnumToRoleEnum1(com.itacademy.blog.services.DTO.RoleDTO.RoleEnum roleEnum) {
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

    protected RoleEnum roleEnumToRoleEnum2(com.itacademy.blog.data.entity.Role.RoleEnum roleEnum) {
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

    protected Role roleToRole(com.itacademy.blog.data.entity.Role role) {
        if ( role == null ) {
            return null;
        }

        Role role1 = new Role();

        role1.setRole( roleEnumToRoleEnum2( role.getRole() ) );

        return role1;
    }
}
