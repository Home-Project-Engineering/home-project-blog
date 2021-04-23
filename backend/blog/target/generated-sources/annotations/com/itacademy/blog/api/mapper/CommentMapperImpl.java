package com.itacademy.blog.api.mapper;

import com.itacademy.blog.data.entity.User.UserBuilder;
import com.itacademy.blog.model.Comment;
import com.itacademy.blog.model.User;
import com.itacademy.blog.model.User.RoleEnum;
import com.itacademy.blog.services.DTO.CommentDTO;
import com.itacademy.blog.services.DTO.CommentDTO.CommentDTOBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-24T01:17:04+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDTO convert(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTOBuilder commentDTO = CommentDTO.builder();

        commentDTO.user( userToUser( comment.getUser() ) );
        if ( comment.getId() != null ) {
            commentDTO.id( comment.getId().longValue() );
        }
        commentDTO.text( comment.getText() );
        commentDTO.createdOn( comment.getCreatedOn() );
        commentDTO.updatedOn( comment.getUpdatedOn() );

        return commentDTO.build();
    }

    @Override
    public Comment convert(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setUser( userToUser1( commentDTO.getUser() ) );
        if ( commentDTO.getId() != null ) {
            comment.setId( BigDecimal.valueOf( commentDTO.getId() ) );
        }
        comment.setText( commentDTO.getText() );
        comment.setCreatedOn( commentDTO.getCreatedOn() );
        comment.setUpdatedOn( commentDTO.getUpdatedOn() );

        return comment;
    }

    @Override
    public List<CommentDTO> convert(List<Comment> commentEntities) {
        if ( commentEntities == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( commentEntities.size() );
        for ( Comment comment : commentEntities ) {
            list.add( convert( comment ) );
        }

        return list;
    }

    protected com.itacademy.blog.data.entity.User.RoleEnum roleEnumToRoleEnum(RoleEnum roleEnum) {
        if ( roleEnum == null ) {
            return null;
        }

        com.itacademy.blog.data.entity.User.RoleEnum roleEnum1;

        switch ( roleEnum ) {
            case BLOGGER: roleEnum1 = com.itacademy.blog.data.entity.User.RoleEnum.BLOGGER;
            break;
            case MODERATOR: roleEnum1 = com.itacademy.blog.data.entity.User.RoleEnum.MODERATOR;
            break;
            case ADMIN: roleEnum1 = com.itacademy.blog.data.entity.User.RoleEnum.ADMIN;
            break;
            case EXPERT: roleEnum1 = com.itacademy.blog.data.entity.User.RoleEnum.EXPERT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + roleEnum );
        }

        return roleEnum1;
    }

    protected com.itacademy.blog.data.entity.User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserBuilder user1 = com.itacademy.blog.data.entity.User.builder();

        if ( user.getId() != null ) {
            user1.id( user.getId().longValue() );
        }
        user1.name( user.getName() );
        user1.firstName( user.getFirstName() );
        user1.lastName( user.getLastName() );
        user1.email( user.getEmail() );
        user1.password( user.getPassword() );
        user1.role( roleEnumToRoleEnum( user.getRole() ) );

        return user1.build();
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

    protected User userToUser1(com.itacademy.blog.data.entity.User user) {
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
}
