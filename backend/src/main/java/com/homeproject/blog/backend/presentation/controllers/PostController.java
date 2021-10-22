package com.homeproject.blog.backend.presentation.controllers;

import com.homeproject.blog.backend.business.models.DTO.Post;
import com.homeproject.blog.backend.business.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("posts")
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);


    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createPost(@RequestBody Post post) {
        LOG.info("Create new post request");
        Post newPost = postService.createPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getPostById(@PathVariable Long id) {
        LOG.info("Get post by id request");
            Post post = postService.readPost(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post changes) {
        LOG.info("Update post request");
            Post newPost = postService.updatePost(id, changes);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePost(@PathVariable Long id) {
        LOG.info("Delete post request");
        postService.deletePost(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
