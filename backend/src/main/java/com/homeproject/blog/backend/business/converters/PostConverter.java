package com.homeproject.blog.backend.business.converters;

import com.homeproject.blog.backend.business.models.DTO.PostDTO;
import com.homeproject.blog.backend.persistence.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostConverter {
    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private TagConverter tagConverter;

    public PostDTO entityToPost(PostEntity entity) {
        PostDTO post = new PostDTO();
        post.setId(entity.getId());
        post.setTitle(entity.getTitle());
        post.setText(entity.getText());
        post.setCreatedOn(entity.getCreatedOn());
        post.setAuthorDTO(authorConverter.entityToAuthor(entity.getAuthorDTO()));
        post.setTags(tagConverter.entitiesToTags(entity.getTags()));
        post.setPreviewAttachment(entity.getPreviewAttachment());
        post.setUpdatedOn(entity.getUpdatedOn());
        return post;
    }

    public PostEntity postToEntity(PostDTO post) {
        PostEntity entity = new PostEntity();
        entity.setId(post.getId());
        entity.setTitle(post.getTitle());
        entity.setText(post.getText());
        entity.setCreatedOn(post.getCreatedOn());
        entity.setAuthorDTO(authorConverter.authorToEntity(post.getAuthorDTO()));
        entity.setTags(tagConverter.tagsToEntities(post.getTags()));
        entity.setPreviewAttachment(post.getPreviewAttachment());
        entity.setUpdatedOn(post.getUpdatedOn());
        return entity;
    }

    public Collection<PostDTO> entitiesToPosts(Collection<PostEntity> entities) {
        Stream<PostDTO> posts = entities.stream().map(this::entityToPost);
        return posts.collect(Collectors.toCollection(ArrayList::new));
    }
}
