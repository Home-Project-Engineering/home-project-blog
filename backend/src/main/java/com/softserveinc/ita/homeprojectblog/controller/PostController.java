package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.api.PostsApi;
import com.softserveinc.ita.homeprojectblog.model.Comment;
import com.softserveinc.ita.homeprojectblog.model.Post;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class PostController implements PostsApi {

    NativeWebRequest request;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Comment> createComment(BigDecimal postId, Comment comment) {
        return PostsApi.super.createComment(postId, comment);
    }

    // TODO this
    @Override
    public ResponseEntity<Post> createPost(Post body) {
        return null;
    }

    @Override
    public ResponseEntity<Comment> getComment(BigDecimal postId, BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Comment>> getComments(BigDecimal postId, BigDecimal id, String authorName, String sort, Integer pageNum, Integer pageSize) {
        return PostsApi.super.getComments(postId, id, authorName, sort, pageNum, pageSize);
    }

    @Override
    public ResponseEntity<Post> getPost(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Post>> getPosts(BigDecimal id, String tagId, String tagName, String userId, String sort, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeComment(BigDecimal postId, BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removePost(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Comment> updateComment(BigDecimal postId, BigDecimal id, Comment comment) {
        return PostsApi.super.updateComment(postId, id, comment);
    }

    @Override
    public ResponseEntity<Post> updatePost(BigDecimal id, Post post) {
        return PostsApi.super.updatePost(id, post);
    }
}
