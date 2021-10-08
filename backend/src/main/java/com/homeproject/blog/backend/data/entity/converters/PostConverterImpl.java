package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.PostEntity;
import com.homeproject.blog.backend.dtos.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostConverterImpl implements PostConverter {
    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private TagConverter tagConverter;

    @Override
    public Post entityToPost(PostEntity entity) {
        Post post = new Post();
        post.setId(entity.getId());
        post.setTitle(entity.getTitle());
        post.setText(entity.getText());
        post.setCreatedOn(entity.getCreatedOn());
        post.setAuthor(authorConverter.entityToAuthor(entity.getAuthor()));
        post.setTags(tagConverter.entitiesToTags(entity.getTags()));
        post.setPreviewAttachment(entity.getPreviewAttachment());
        post.setUpdatedOn(entity.getUpdatedOn());
        return post;
    }

    @Override
    public PostEntity postToEntity(Post post) {
        PostEntity entity = new PostEntity();
        entity.setId(post.getId());
        entity.setTitle(post.getTitle());
        entity.setText(post.getText());
        entity.setCreatedOn(post.getCreatedOn());
        entity.setAuthor(authorConverter.authorToEntity(post.getAuthor()));
        entity.setTags(tagConverter.tagsToEntities(post.getTags()));
        entity.setPreviewAttachment(post.getPreviewAttachment());
        entity.setUpdatedOn(post.getUpdatedOn());
        return entity;
    }

    @Override
    public Collection<Post> entitiesToPosts(Collection<PostEntity> entities) {
        Stream<Post> posts = entities.stream().map(this::entityToPost);
        return posts.collect(Collectors.toCollection(ArrayList::new));
    }
}