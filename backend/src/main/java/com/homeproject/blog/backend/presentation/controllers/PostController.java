package com.homeproject.blog.backend.presentation.controllers;

import com.homeproject.blog.backend.business.exceptions.NotFoundException;
import com.homeproject.blog.backend.business.models.DTO.PostDTO;
import com.homeproject.blog.backend.business.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RequestMapping("posts")
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllPosts(@RequestParam(required = false) Map<String, String> parameters) {
        LOG.info("Get all posts request");
        Collection<PostDTO> postDTOS = postService.getPosts(parameters);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createPost(@RequestBody PostDTO postDTO) {
        LOG.info("Create new post request");
        PostDTO newPostDTO = postService.createPost(postDTO);
        return new ResponseEntity<>(newPostDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getPostById(@PathVariable Long id) {
        LOG.info("Get post by id request");
        try {
            PostDTO postDTO = postService.readPost(id);
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        }catch (NotFoundException exception){
            LOG.info(exception.getMessage());
            return new ResponseEntity<>(new RuntimeException(exception.getMessage()),exception.getHttpStatus());
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody PostDTO changes) {
        LOG.info("Update post request");
        PostDTO newPostDTO = postService.updatePost(id, changes);
        return new ResponseEntity<>(newPostDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePost(@PathVariable Long id) {
        LOG.info("Delete post request");
        postService.deletePost(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
