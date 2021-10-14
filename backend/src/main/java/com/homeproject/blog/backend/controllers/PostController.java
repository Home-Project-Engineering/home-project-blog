package com.homeproject.blog.backend.controllers;

import com.homeproject.blog.backend.services.PostService;
import com.homeproject.blog.backend.dto.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("posts")
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

    @PostMapping(produces = "application/json")
    ResponseEntity createPost (@RequestBody PostDTO dto) {
        LOG.info(dto.getText());
        ResponseEntity.ok(dto.getText());
        return ResponseEntity.ok(dto.getAuthorId());
    }

    @GetMapping(produces = "application/json")
    ResponseEntity takePost(){
        return ResponseEntity.ok(postService.getPost());
    }

    @GetMapping(produces = "application/json")
    ResponseEntity getAllPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

}
