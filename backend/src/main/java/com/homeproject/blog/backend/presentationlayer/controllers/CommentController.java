package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Error;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllComments(@RequestParam(required = false) Map<String, String> parameters) {
        LOG.info("Get all comments request");
        Collection<Comment> comments = commentService.getComments();
        comments = commentService.sortComments(comments, parameters);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createComment(@RequestBody Comment comment) {
        LOG.info("Create comment request");
        Comment newComment = commentService.createComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getCommentById(@PathVariable(name = "id") Long id) throws CommentNotFoundException {
        LOG.info("Get comment by id request");
        Comment comment = commentService.readComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updateComment(@PathVariable(name = "id") Long id, @RequestBody Comment changes) throws CommentNotFoundException {
        Comment comment = commentService.updateComment(id, changes);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deleteComment(@PathVariable(name = "id") Long id) throws CommentNotFoundException {
        commentService.deleteComment(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
