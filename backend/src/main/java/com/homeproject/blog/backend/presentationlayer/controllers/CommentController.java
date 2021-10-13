package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.services.CommentService;
import com.homeproject.blog.backend.data.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts/{post_id}/comments")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> createComment(@PathVariable Long post_id, @RequestBody Comment comment){
        return ResponseEntity.status(HttpStatus.CREATED).body(createComment(post_id, comment));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getComments (@PathVariable Long post_id){
        return ResponseEntity.ok(commentService.getComments(post_id));
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long post_id, @PathVariable Long id){
        return commentService.readComment(post_id, id);
    }
}
