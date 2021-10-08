package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.dtos.Comment;

public interface CommentConverter {

    Comment entityToComment(CommentEntity entity);

    CommentEntity commentToEntity(Comment comment);
}
