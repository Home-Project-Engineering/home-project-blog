package com.itacademy.blog.services.service;

import com.itacademy.blog.data.entity.Comment;
import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.services.DTO.CommentDTO;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.exception.AlreadyExistBlogException;
import com.itacademy.blog.services.exception.NotFoundBlogException;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO createCommentDto);

    CommentDTO updateComment(Long postId, Long id, CommentDTO updateCommentDto);

    List<CommentDTO> findComments(Integer pageNumber, Integer pageSize, String sort, Specification<Comment> specification);

    CommentDTO getCommentById(Long postId, Long id);

    CommentDTO deleteComment(Long postId, Long id) ;
}
