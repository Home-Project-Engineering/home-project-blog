package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.services.CommentService;
import com.homeproject.blog.backend.businesslayer.services.PostService;
import com.homeproject.blog.backend.presentationlayer.config.ParametersConfig;
import com.homeproject.blog.backend.presentationlayer.model.Comment;
import com.homeproject.blog.backend.presentationlayer.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PostController implements PostsApi{

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConversionService conversionService;


    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Post> createPost(Post post){
        PostDTO postDTO = postService.createPost(conversionService.convert(post, PostDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(postDTO, Post.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Comment> createComment(Long postId, Comment comment){
        CommentDTO commentDTO = commentService.createComment(postId, conversionService.convert(comment, CommentDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(commentDTO, Comment.class));
    }

    @Override
    public ResponseEntity<List<Post>> getPosts(Long id, Long tagId, String tagName, String authorName,  String sort, Integer pageNum, Integer pageSize) {
        Page<PostDTO> page = postService.getPosts(id, tagId, tagName, authorName, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(post -> conversionService.convert(post, Post.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<Comment>> getComments(Long postId, Long id, String authorName, String sort, Integer pageNum, Integer pageSize) {
        Page<CommentDTO> page = commentService.getComments(postId, id, authorName, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(comment -> conversionService.convert(comment, Comment.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Post> getPost(Long id){
        return ResponseEntity.ok(conversionService.convert(postService.readPost(id), Post.class));
    }

    @Override
    public ResponseEntity<Comment> getComment(Long postId, Long id){
        return ResponseEntity.ok(conversionService.convert(commentService.readComment(postId, id), Comment.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR')")
    public ResponseEntity<Post> updatePost( Long id, Post post) {
        PostDTO postDTO = postService.updatePost(id, conversionService.convert(post, PostDTO.class));
        return ResponseEntity.ok(conversionService.convert(postDTO, Post.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR')")
    public ResponseEntity<Comment> updateComment(Long postId, Long id, Comment comment) {
        CommentDTO commentDTO = commentService.updateComment(postId, id, conversionService.convert(comment, CommentDTO.class));
        return ResponseEntity.ok(conversionService.convert(commentDTO, Comment.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR')")
    public ResponseEntity<Void> removePost(Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR')")
    public ResponseEntity<Void> removeComment(Long postId, Long id) {
        commentService.deleteComment(postId, id);
        return ResponseEntity.noContent().build();
    }
}