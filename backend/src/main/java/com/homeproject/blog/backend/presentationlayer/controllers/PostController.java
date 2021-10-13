package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.businesslayer.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> createPost(@RequestBody PostDTO post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(post));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getPosts(@RequestParam( name = "id", required = false) Long id, @RequestParam(name = "tag_id", required = false) Long tagId, @RequestParam(name = "tag_name", required = false) String tagName, @RequestParam(name = "author_name", required = false) String authorName, @RequestParam(name = "sort", required = false) String sort, @RequestParam(name = "page_num", required = false, defaultValue = "0") Integer pageNum, @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer pageSize) {
        Page<PostDTO> page = postService.getPosts(id, tagId, tagName, authorName, PageRequest.of(pageNum, pageSize, Sort.by("id")));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.readPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody PostDTO post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public void removePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}