package com.homeproject.blog.backend.business.convertersBetweenServiceAndController;

import com.homeproject.blog.backend.business.models.DTO.Post;
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
    private TagConverter tagConverter;

    public Post entityToPost(PostEntity entity) {
        Post post = new Post();
        post.setId(entity.getId());
        post.setText(entity.getText());
        post.setTags(tagConverter.entitiesToTags(entity.getTags()));
        return post;
    }

    public PostEntity postToEntity(Post post) {
        PostEntity entity = new PostEntity();
        entity.setId(post.getId());
        entity.setText(post.getText());
        entity.setTags(tagConverter.tagsToEntities(post.getTags()));
        return entity;
    }

    public Collection<Post> entitiesToPosts(Collection<PostEntity> entities) {
        Stream<Post> posts = entities.stream().map(this::entityToPost);
        return posts.collect(Collectors.toCollection(ArrayList::new));
    }
}
