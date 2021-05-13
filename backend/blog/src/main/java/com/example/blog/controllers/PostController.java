package com.example.blog.controllers;


import com.example.blog.generated.api.PostsApi;
import com.example.blog.generated.model.Comment;
import com.example.blog.generated.model.Post;
import com.example.blog.services.PostService;
import com.example.blog.util.dtos.DtoComment;
import com.example.blog.util.dtos.DtoPost;
import com.example.blog.util.mappers.CommentMapper;
import com.example.blog.util.mappers.PostMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class PostController implements PostsApi {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    @PermitAll
    public ResponseEntity<Comment> createComment(@PathVariable("post_id") BigDecimal postId, Comment comment) {
        DtoComment dtoComment = CommentMapper.INSTANCE.toDto(comment);
        DtoComment newComment = postService.createComment(postId.longValue(), dtoComment);

        Comment createdComment = CommentMapper.INSTANCE.toModel(newComment);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> createPost(Post post) {
        DtoPost dtoPost = PostMapper.INSTANCE.toDto(post);
        DtoPost dtoCreated = postService.createPost(dtoPost);
        Post p = PostMapper.INSTANCE.toModel(dtoCreated);

        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Comment> getComment(BigDecimal postId, BigDecimal id) {
        DtoComment dtoComment = postService.getComment(postId.longValue(), id.longValue());
        Comment comment = CommentMapper.INSTANCE.toModel(dtoComment);

        return new ResponseEntity<>(comment, HttpStatus.OK);

    }
    @Override
    public ResponseEntity<List<Comment>> getComments(@PathVariable("post_id") BigDecimal postId,
                                                     BigDecimal id,
                                                     @PathVariable("author_name") String authorName,
                                                     String sort,
                                                     @PathVariable("page_num") Integer pageNum,
                                                     @PathVariable("page_size") Integer pageSize) {
        Page<DtoComment> dtoComments;
        if (id != null) {
            dtoComments = postService.getAllComments(postId.longValue(), id.longValue(), authorName, sort, pageNum, pageSize);
        } else {
            dtoComments = postService.getAllComments(postId.longValue(), null, authorName, sort, pageNum, pageSize);
        }

        List<Comment> comments = CommentMapper.INSTANCE.toModels(dtoComments.getContent());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", String.valueOf(dtoComments.getTotalElements()));

        return new ResponseEntity<>(comments, httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> getPost(BigDecimal id) {

        DtoPost dtoPost = postService.getPost(id.longValue());
        Post post = PostMapper.INSTANCE.toModel(dtoPost);


        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Post>> getPosts(@PathVariable("post_id") BigDecimal id,
                                               @PathVariable("tag_id") String tagId,
                                               @PathVariable("tag_name") String tagName,
                                               @PathVariable("user_id") String userId,
                                               String sort,
                                               @PathVariable("page_num") Integer pageNum,
                                               @PathVariable("page_size") Integer pageSize) {

        Page<DtoPost> dtoPosts;
        if (id != null) {
            dtoPosts = postService.getPosts(id.longValue(), tagId, tagName, userId, sort, pageNum, pageSize);
        } else {
            dtoPosts = postService.getPosts(null, tagId, tagName, userId, sort, pageNum, pageSize);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", String.valueOf(dtoPosts.getTotalElements()));

        List<Post> posts = PostMapper.INSTANCE.toModels(dtoPosts.getContent());

        return new ResponseEntity<>(posts, httpHeaders, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('update:posts:comment:tag')")
    public ResponseEntity<Void> removeComment(BigDecimal postId, BigDecimal id) {

        postService.removeComment(postId.longValue(), id.longValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    @PreAuthorize("hasAuthority('update:posts:comment:tag')")
    public ResponseEntity<Void> removePost(BigDecimal id) {

        postService.removePost(id.longValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAuthority('update:posts:comment:tag')")
    public ResponseEntity<Comment> updateComment(BigDecimal postId, BigDecimal id, Comment comment) {

        DtoComment dtoComment = CommentMapper.INSTANCE.toDto(comment);
        DtoComment updatedDtoComment = postService.updateComment(postId.longValue(), id.longValue(), dtoComment);

        Comment updatedComment = CommentMapper.INSTANCE.toModel(updatedDtoComment);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('update:posts:comment:tag')")
    public ResponseEntity<Post> updatePost(BigDecimal id, Post post) {

        DtoPost dtoPost = PostMapper.INSTANCE.toDto(post);
        DtoPost dtoUpdatedPost = postService.updatePost(id.longValue(), dtoPost);

        Post updatedPost = PostMapper.INSTANCE.toModel(dtoUpdatedPost);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
}
