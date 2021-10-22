package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.Post;

import java.util.List;

public interface PostConverter {

    com.homeproject.blog.backend.presentationlayer.model.Post dtoToView(Post dto);

    Post viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Post view);

    List<com.homeproject.blog.backend.presentationlayer.model.Post> dtosToViews(List<Post> dtos);

    List<Post> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Post> views);
}
