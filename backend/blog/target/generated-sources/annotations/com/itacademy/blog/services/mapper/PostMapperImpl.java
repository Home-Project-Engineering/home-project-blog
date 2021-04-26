package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.services.DTO.PostDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-25T23:17:27+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDTO convert(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setUser( userToUser( post.getUser() ) );
        postDTO.setId( post.getId() );
        List<Tag> list = post.getTags();
        if ( list != null ) {
            postDTO.setTags( new ArrayList<Tag>( list ) );
        }
        postDTO.setCreatedOn( post.getCreatedOn() );
        postDTO.setText( post.getText() );
        postDTO.setTitle( post.getTitle() );
        postDTO.setPreviewAttachment( post.getPreviewAttachment() );
        postDTO.setUpdatedOn( post.getUpdatedOn() );

        return postDTO;
    }

    @Override
    public Post convert(PostDTO postDTO) {
        if ( postDTO == null ) {
            return null;
        }

        Post post = new Post();

        post.setUser( postDTO.getUser() );
        post.setId( postDTO.getId() );
        List<Tag> list = postDTO.getTags();
        if ( list != null ) {
            post.setTags( new ArrayList<Tag>( list ) );
        }
        post.setCreatedOn( postDTO.getCreatedOn() );
        post.setText( postDTO.getText() );
        post.setTitle( postDTO.getTitle() );
        post.setPreviewAttachment( postDTO.getPreviewAttachment() );
        post.setUpdatedOn( postDTO.getUpdatedOn() );

        return post;
    }

    @Override
    public List<PostDTO> convert(List<Post> postEntities) {
        if ( postEntities == null ) {
            return null;
        }

        List<PostDTO> list = new ArrayList<PostDTO>( postEntities.size() );
        for ( Post post : postEntities ) {
            list.add( convert( post ) );
        }

        return list;
    }

    protected User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );
        user1.setName( user.getName() );
        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setEmail( user.getEmail() );
        user1.setRole( user.getRole() );

        user1.setPassword( "********" );

        return user1;
    }
}
