package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;
    private final Logger LOG = LoggerFactory.getLogger(PostController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllPosts(@RequestParam(required = false) Map<String, String> parameters) {
        LOG.info("Get all posts request");
        Page<Post> page = postService.getPosts(parameters);
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.toList());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createPost(@RequestBody Post post) {
        LOG.info("Create new post request");
        Post newPost = postService.createPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
     ResponseEntity<Object> getPostById(@PathVariable Long id) throws PostNotFoundException {
        LOG.info("Get post by id request");
        Post post = postService.readPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post changes) throws PostNotFoundException {
        LOG.info("Update post request");
        Post newPost = postService.updatePost(id, changes);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePost(@PathVariable Long id) throws PostNotFoundException {
        LOG.info("Delete post request");
        postService.deletePost(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
