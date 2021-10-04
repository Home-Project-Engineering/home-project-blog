package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.entities.Comment;
import com.homeproject.blog.backend.entities.Error;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("posts/{post_id}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllComments(@RequestParam(required = false) Map<String, String> parameters) {
        Collection<Comment> comments = commentService.getComments();
        comments = commentService.sortComments(comments, parameters);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createComment(@RequestBody Comment comment) {
        Comment newComment = commentService.createComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getCommentById(@PathVariable(name = "id") Long id) {
        try {
            Comment comment = commentService.readComment(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (CommentNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updateComment(@PathVariable(name = "id") Long id, @RequestBody Comment changes) {
        try {
            Comment comment = commentService.updateComment(id, changes);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (CommentNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePost(@PathVariable(name = "id") Long id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (CommentNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
