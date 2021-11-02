package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.Comment;

import java.util.List;

public interface CommentConverter {

    com.homeproject.blog.backend.presentationlayer.model.Comment dtoToView(Comment dto);

    Comment viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Comment view);

    List<com.homeproject.blog.backend.presentationlayer.model.Comment> dtosToViews(List<Comment> dtos);

    List<Comment> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Comment> views);
}
