package com.homeproject.blog.backend.business.services;

import com.homeproject.blog.backend.business.models.DTO.CommentDTO;

import java.util.Collection;

public interface CommentService {

    CommentDTO createComment(CommentDTO comment);

    CommentDTO updateComment(Long id, CommentDTO commentUpdate);

    CommentDTO readComment(Long id);

    void deleteComment(Long id);

    CommentDTO getCommentById(Long id);

    Collection<CommentDTO> getComments();
}
