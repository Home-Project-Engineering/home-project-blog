package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.services.DTO.PostDTO;
import com.itacademy.blog.services.DTO.PostDTO.PostDTOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-16T22:02:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDTO convert(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTOBuilder postDTO = PostDTO.builder();

        postDTO.id( post.getId() );
        List<Tag> list = post.getTags();
        if ( list != null ) {
            postDTO.tags( new ArrayList<Tag>( list ) );
        }
        postDTO.createdOn( post.getCreatedOn() );
        postDTO.author( post.getAuthor() );
        postDTO.text( post.getText() );
        postDTO.title( post.getTitle() );
        postDTO.previewAttachment( post.getPreviewAttachment() );
        postDTO.updatedOn( post.getUpdatedOn() );

        return postDTO.build();
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
