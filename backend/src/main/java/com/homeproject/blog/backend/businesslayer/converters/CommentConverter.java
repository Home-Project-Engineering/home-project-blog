package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.dtos.Comment;

import java.util.List;

public interface CommentConverter {

    Comment entityToComment(CommentEntity entity);

    CommentEntity commentToEntity(Comment comment);
}
