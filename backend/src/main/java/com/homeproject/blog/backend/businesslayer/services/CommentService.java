package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import org.springframework.data.domain.Page;

public interface CommentService {

    CommentDTO createComment(Long postId, CommentDTO comment);

    CommentDTO updateComment(Long postId, Long id, CommentDTO comment);

    CommentDTO readComment(Long postId, Long id);

    Page<CommentDTO> getComments(Long postId);

    void deleteComment(Long postId, Long id);
}
