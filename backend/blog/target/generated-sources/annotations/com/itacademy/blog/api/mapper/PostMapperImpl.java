package com.itacademy.blog.api.mapper;

import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.model.Post;
import com.itacademy.blog.services.DTO.PostDTO;
import java.math.BigDecimal;
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

        if ( post.getId() != null ) {
            postDTO.setId( post.getId().longValue() );
        }
        postDTO.setTags( tagListToTagList( post.getTags() ) );
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

        if ( postDTO.getId() != null ) {
            post.setId( BigDecimal.valueOf( postDTO.getId() ) );
        }
        post.setTags( tagListToTagList1( postDTO.getTags() ) );
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

    protected Tag tagToTag(com.itacademy.blog.model.Tag tag) {
        if ( tag == null ) {
            return null;
        }

        Tag tag1 = new Tag();

        if ( tag.getId() != null ) {
            tag1.setId( tag.getId().longValue() );
        }
        tag1.setName( tag.getName() );

        return tag1;
    }

    protected List<Tag> tagListToTagList(List<com.itacademy.blog.model.Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<Tag> list1 = new ArrayList<Tag>( list.size() );
        for ( com.itacademy.blog.model.Tag tag : list ) {
            list1.add( tagToTag( tag ) );
        }

        return list1;
    }

    protected com.itacademy.blog.model.Tag tagToTag1(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        com.itacademy.blog.model.Tag tag1 = new com.itacademy.blog.model.Tag();

        if ( tag.getId() != null ) {
            tag1.setId( BigDecimal.valueOf( tag.getId() ) );
        }
        tag1.setName( tag.getName() );

        return tag1;
    }

    protected List<com.itacademy.blog.model.Tag> tagListToTagList1(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<com.itacademy.blog.model.Tag> list1 = new ArrayList<com.itacademy.blog.model.Tag>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagToTag1( tag ) );
        }

        return list1;
    }
}
