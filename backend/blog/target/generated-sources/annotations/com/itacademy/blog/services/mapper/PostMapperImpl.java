package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.services.DTO.PostDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-22T13:30:44+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDTO convert(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setId( post.getId() );
        List<Tag> list = post.getTags();
        if ( list != null ) {
            postDTO.setTags( new ArrayList<Tag>( list ) );
        }
        postDTO.setCreatedOn( post.getCreatedOn() );
        postDTO.setAuthor( post.getAuthor() );
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

        post.setId( postDTO.getId() );
        List<Tag> list = postDTO.getTags();
        if ( list != null ) {
            post.setTags( new ArrayList<Tag>( list ) );
        }
        post.setCreatedOn( postDTO.getCreatedOn() );
        post.setAuthor( postDTO.getAuthor() );
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
}
