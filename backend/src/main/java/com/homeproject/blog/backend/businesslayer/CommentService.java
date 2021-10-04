package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.entities.Comment;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;;

import java.util.Collection;
import java.util.Map;

public interface CommentService {

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment changes) throws CommentNotFoundException;

    Comment readComment(Long id) throws CommentNotFoundException;

    Collection<Comment> getComments();

    Collection<Comment> sortComments(Collection<Comment> comments, Map<String, String> parameters);

    void deleteComment(Long id) throws CommentNotFoundException;
}
