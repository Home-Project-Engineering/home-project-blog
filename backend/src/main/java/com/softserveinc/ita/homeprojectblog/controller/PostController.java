package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.api.PostsApi;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperController;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperController;
import com.softserveinc.ita.homeprojectblog.model.Comment;
import com.softserveinc.ita.homeprojectblog.model.Post;
import com.softserveinc.ita.homeprojectblog.service.CommentService;
import com.softserveinc.ita.homeprojectblog.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

    PostMapperController postMapper;
    CommentMapperController commentMapper;

    PostService postService;
    CommentService commentService;

    NativeWebRequest request;


    @Override // +/-
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override // +
    public ResponseEntity<Comment> createComment(BigDecimal postId, Comment comment) {
        var commentDto = commentMapper.toCommentDto(comment);
        commentDto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(commentMapper.toComment(commentDto), HttpStatus.CREATED);
    }

    @Override // +
    public ResponseEntity<Post> createPost(Post body) {
        var postDto = postMapper.toPostDto(body);
        postDto = postService.createPost(postDto);
        return new ResponseEntity<>(postMapper.toPost(postDto), HttpStatus.CREATED);
    }

    @Override // +
    public ResponseEntity<Comment> getComment(BigDecimal postId, BigDecimal id) {
        var commentDto = commentService.getComment(postId, id);
        return new ResponseEntity<>(commentMapper.toComment(commentDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<List<Comment>> getComments(
            BigDecimal postId, BigDecimal id, String authorName,
            String sort, Integer pageNum, Integer pageSize) {

        var commentDtoPage = commentService.getComment(
                postId, id, authorName,
                sort, pageNum, pageSize);

        var pageComment = commentMapper.toCommentPage(commentDtoPage);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(pageComment.getTotalElements()));

        return new ResponseEntity<>(pageComment.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<Post> getPost(BigDecimal id) {
        var postDto = postService.getPost(id);
        return new ResponseEntity<>(postMapper.toPost(postDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<List<Post>> getPosts(
            BigDecimal id, String tagId, String tagName, String authorName,
            String sort, Integer pageNum, Integer pageSize) {

        var postDtoPage = postService.getPosts(
                id, tagId, tagName, authorName,
                sort, pageNum, pageSize
        );

        var postPage = postMapper.toPagePostDto(postDtoPage);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(postPage.getTotalElements()));

        return new ResponseEntity<>(postPage.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<Void> removeComment(BigDecimal postId, BigDecimal id) {
        commentService.removeComment(postId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    public ResponseEntity<Void> removePost(BigDecimal id) {
        postService.removePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    public ResponseEntity<Comment> updateComment(BigDecimal postId, BigDecimal id, Comment comment) {
        var commentDto = commentMapper.toCommentDto(comment);
        var updatedCommentDto = commentService.updateComment(postId, id, commentDto);
        return new ResponseEntity<>(commentMapper.toComment(updatedCommentDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<Post> updatePost(BigDecimal id, Post post) {
        var postDto = postMapper.toPostDto(post);
        var changedPostDto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(postMapper.toPost(changedPostDto), HttpStatus.OK);
    }
}
