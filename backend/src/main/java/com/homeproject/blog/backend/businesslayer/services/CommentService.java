package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentDTO createComment(Long postId, CommentDTO comment);

    CommentDTO updateComment(Long postId,Long id,CommentDTO comment);

    CommentDTO readComment(Long postId,Long id);

    Page<CommentDTO> getComments(Long postId, Long id, String authorName, Pageable pageRequest);

    void deleteComment(Long postId,Long id);

    CommentDTO getCommentByCurrentUser(Long id);

    Page<CommentDTO> getCommentsByCurrentUser(Long id, Pageable pageRequest);

    CommentDTO updateCommentByCurrentUser(Long id,CommentDTO comment);

    void deleteCommentByCurrentUser(Long id);
}
