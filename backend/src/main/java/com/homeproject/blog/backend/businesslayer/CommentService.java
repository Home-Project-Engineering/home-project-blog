package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import org.springframework.data.domain.Page;;

import java.util.Collection;
import java.util.Map;

public interface CommentService {

    Comment createComment(Comment comment, Long postId);

    Comment updateComment(Long id, Comment changes);

    Comment readComment(Long id, Long postId);

    void deleteComment(Long id, Long postId);

    Page<Comment> findAll(Long postId, Long id, String authorName, Integer pageNum, Integer pageSize, String sort);
}
