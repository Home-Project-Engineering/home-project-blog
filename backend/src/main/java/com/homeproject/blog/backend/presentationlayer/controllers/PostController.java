package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("posts")
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(produces = "application/json")
    ResponseEntity getAllPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

}
