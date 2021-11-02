package com.homeproject.blog.backend.presentation.controllers;

import com.homeproject.blog.backend.business.models.DTO.CommentDTO;
import com.homeproject.blog.backend.business.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);


    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllComments() {
        LOG.info("Get all comments request");
        Collection<CommentDTO> comments = commentService.getComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createComment(@RequestBody CommentDTO comment) {
        LOG.info("Create comment request");
        CommentDTO newComment = commentService.createComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getCommentById(@PathVariable(name = "id") Long id) {
        LOG.info("Get comment by id request");
            CommentDTO comment = commentService.readComment(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updateComment(@PathVariable(name = "id") Long id, @RequestBody CommentDTO changes) {
            CommentDTO comment = commentService.updateComment(id, changes);
            return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deleteComment(@PathVariable(name = "id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
