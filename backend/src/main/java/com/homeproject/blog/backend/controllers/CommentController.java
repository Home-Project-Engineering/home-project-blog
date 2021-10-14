package com.homeproject.blog.backend.controllers;

import com.homeproject.blog.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping()
    public ResponseEntity getCommentById(Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }
}
