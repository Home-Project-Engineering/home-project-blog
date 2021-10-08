package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.dtos.Error;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
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
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;
    private final Logger LOG = LoggerFactory.getLogger(PostController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllPosts(@RequestParam(required = false) Map<String, String> parameters) {
        LOG.info("Get all posts request");
        Collection<Post> posts = postService.getPosts(parameters);
        posts = postService.sortPosts(posts, parameters);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createPost(@RequestBody Post post) {
        LOG.info("Create new post request");
        Post newPost = postService.createPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
     ResponseEntity<Object> getPostById(@PathVariable Long id) {
        LOG.info("Get post by id request");
        try {
            Post post = postService.readPost(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            LOG.info("Exception " + exception.getMessage());
            return new ResponseEntity<>(new Error(exception.getCode(), exception.getMessage()), exception.getHttpStatus());
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post changes) {
        LOG.info("Update post request");
        try {
            Post newPost = postService.updatePost(id, changes);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            LOG.info("Exception " + exception.getMessage());
            return new ResponseEntity<>(new Error(exception.getCode(), exception.getMessage()), exception.getHttpStatus());
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePost(@PathVariable Long id) {
        LOG.info("Delete post request");
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (PostNotFoundException exception) {
            LOG.info("Exception " + exception.getMessage());
            return new ResponseEntity<>(new Error(exception.getCode(), exception.getMessage()), exception.getHttpStatus());
        }
    }
}
