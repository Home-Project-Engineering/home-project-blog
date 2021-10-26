package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.services.PostService;
import com.homeproject.blog.backend.presentationlayer.config.ParametersConfig;
import com.homeproject.blog.backend.presentationlayer.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PostController implements PostsApi{

    @Autowired
    private PostService postService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public ResponseEntity<Post> createPost(Post post){
        PostDTO postDTO = postService.createPost(conversionService.convert(post, PostDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(postDTO, Post.class));
    }

    @Override
    public ResponseEntity<List<Post>> getPosts(Long id, Long tagId, String tagName, String authorName,  String sort, Integer pageNum, Integer pageSize) {
        Page<PostDTO> page = postService.getPosts(id, tagId, tagName, authorName, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(post -> conversionService.convert(post, Post.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Post> getPost(Long id){
        return ResponseEntity.ok(conversionService.convert(postService.readPost(id), Post.class));
    }

    @Override
    public ResponseEntity<Post> updatePost( Long id, Post post) {
        PostDTO postDTO = postService.updatePost(id, conversionService.convert(post, PostDTO.class));
        return ResponseEntity.ok(conversionService.convert(postDTO, Post.class));
    }

    @Override
    public ResponseEntity<Void> removePost(Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}