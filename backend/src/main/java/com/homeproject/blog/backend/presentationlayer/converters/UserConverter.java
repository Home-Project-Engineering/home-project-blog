package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.User;

import java.util.List;

public interface UserConverter {
    com.homeproject.blog.backend.presentationlayer.model.User dtoToModel(User dto);

    User modelToDto(com.homeproject.blog.backend.presentationlayer.model.User model);

    List<com.homeproject.blog.backend.presentationlayer.model.User> dtosToViews(List<User> toList);
}
