package com.homeproject.blog.backend.business.services;

import com.homeproject.blog.backend.business.models.DTO.Comment;

import java.util.Collection;

public interface CommentService {

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment commentUpdate);

    Comment readComment(Long id);

    void deleteComment(Long id);

    Comment getCommentById(Long id);

    Collection<Comment> getComments();
}
