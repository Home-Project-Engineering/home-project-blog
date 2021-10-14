package com.homeproject.blog.backend.services;

import com.homeproject.blog.backend.classes.Comment;

public interface CommentService {
    Comment getCommentById(Long id);
}
