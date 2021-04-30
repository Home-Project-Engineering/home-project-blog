package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.generated.api.PostsApi;
import com.softserveinc.ita.homeprojectblog.generated.model.Comment;
import com.softserveinc.ita.homeprojectblog.generated.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController implements PostsApi {
    @Override
    public ResponseEntity<Comment> createComment(Comment body, BigDecimal postId) {
        return null;
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
    public ResponseEntity<List<Comment>> getComments(BigDecimal postId, BigDecimal id, String userName, String userId, String sort, Integer pageNum, Integer pageSize) {
        return null;
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
    public ResponseEntity<Comment> updateComment(Comment body, BigDecimal postId, BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Post> updatePost(Post body, BigDecimal id) {
        return null;
    }
}
