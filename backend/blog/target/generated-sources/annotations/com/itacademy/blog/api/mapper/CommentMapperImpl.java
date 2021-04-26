package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.Comment;
import com.itacademy.blog.model.User;
import com.itacademy.blog.model.User.RoleEnum;
import com.itacademy.blog.services.DTO.CommentDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-25T23:17:27+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDTO convert(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setUser( userToUser( comment.getUser() ) );
        if ( comment.getId() != null ) {
            commentDTO.setId( comment.getId().longValue() );
        }
        commentDTO.setText( comment.getText() );
        commentDTO.setCreatedOn( comment.getCreatedOn() );
        commentDTO.setUpdatedOn( comment.getUpdatedOn() );

        return commentDTO;
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

        com.itacademy.blog.data.entity.User user1 = new com.itacademy.blog.data.entity.User();

        if ( user.getId() != null ) {
            user1.setId( user.getId().longValue() );
        }
        user1.setName( user.getName() );
        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setEmail( user.getEmail() );
        user1.setPassword( user.getPassword() );
        user1.setRole( roleEnumToRoleEnum( user.getRole() ) );

        return user1;
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
