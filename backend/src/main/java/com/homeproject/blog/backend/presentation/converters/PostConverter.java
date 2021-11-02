package com.homeproject.blog.backend.presentation.converters;

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
    private TagConverter tagConverter;

    public PostDTO entityToPost(PostEntity entity) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(entity.getId());
        postDTO.setText(entity.getText());
        postDTO.setTags(tagConverter.entitiesToTags(entity.getTags()));
        return postDTO;
    }

    public PostEntity postToEntity(PostDTO postDTO) {
        PostEntity entity = new PostEntity();
        entity.setId(postDTO.getId());
        entity.setText(postDTO.getText());
        entity.setTags(tagConverter.tagsToEntities(postDTO.getTags()));
        return entity;
    }

    public Collection<PostDTO> entitiesToPosts(Collection<PostEntity> entities) {
        Stream<PostDTO> posts = entities.stream().map(this::entityToPost);
        return posts.collect(Collectors.toCollection(ArrayList::new));
    }
}
