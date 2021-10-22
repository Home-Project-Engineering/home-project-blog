package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import org.springframework.data.domain.Page;;

import java.util.Collection;
import java.util.Map;

public interface CommentService {

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment changes) throws CommentNotFoundException;

    Comment readComment(Long id) throws CommentNotFoundException;

    void deleteComment(Long id) throws CommentNotFoundException;

    Page<Comment> findAll(Long id, String authorName, Integer pageNum, Integer pageSize, String sort);
}
