package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.entities.Post;
import com.homeproject.blog.backend.entities.Error;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
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

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllPosts(@RequestParam(required = false) Map<String, String> parameters) {
        Collection<Post> posts = postService.getPosts();
        posts = postService.sortPosts(posts, parameters);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createPost(@RequestBody Post post) {
        try {
            Post newPost = postService.createPost(post);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (TagNotFoundException ex) {
            return new ResponseEntity<>(new Error("400", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
     ResponseEntity<Object> getPostById(@PathVariable Long id) {
        try {
            Post post = postService.readPost(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post changes) {
        try {
            Post newPost = postService.updatePost(id, changes);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        } catch (TagNotFoundException exception) {
            return new ResponseEntity<>(new Error("400", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (PostNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
