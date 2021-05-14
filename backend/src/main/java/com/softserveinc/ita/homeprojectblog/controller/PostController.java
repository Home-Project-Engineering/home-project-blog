package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.api.PostsApi;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperController;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperController;
import com.softserveinc.ita.homeprojectblog.model.Comment;
import com.softserveinc.ita.homeprojectblog.model.Post;
import com.softserveinc.ita.homeprojectblog.service.CommentService;
import com.softserveinc.ita.homeprojectblog.service.PostService;
import com.softserveinc.ita.homeprojectblog.util.Boilerplate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.security.PermitAll;
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

    Boilerplate boilerplate;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    @PreAuthorize("hasAuthority('role:any-registered')")
    public ResponseEntity<Comment> createComment(BigDecimal postId, Comment comment) {
        var commentDtoSet = commentMapper.toCommentDto(comment);
        var commentDtoGet = commentService.createComment(postId, commentDtoSet);
        return new ResponseEntity<>(commentMapper.toComment(commentDtoGet), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('role:any-registered')")
    public ResponseEntity<Post> createPost(Post body) {
        var postDtoSet = postMapper.toPostDto(body);
        var postDtoGet = postService.createPost(postDtoSet);
        return new ResponseEntity<>(postMapper.toPost(postDtoGet), HttpStatus.CREATED);
    }

    @Override
    @PermitAll
    public ResponseEntity<Comment> getComment(BigDecimal postId, BigDecimal id) {
        var commentDtoGet = commentService.getComment(postId, id);
        return new ResponseEntity<>(commentMapper.toComment(commentDtoGet), HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<List<Comment>> getComments(
            BigDecimal postId, BigDecimal id, String authorName,
            String sort, Integer pageNum, Integer pageSize) {

        var commentDtoPageGet = commentService.getComment(
                postId, id, authorName,
                sort, pageNum, pageSize);

        var commentPageGet = commentMapper.toCommentPage(commentDtoPageGet);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(commentPageGet);
        return new ResponseEntity<>(commentPageGet.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> getPost(BigDecimal id) {
        var postDtoGet = postService.getPost(id);
        return new ResponseEntity<>(postMapper.toPost(postDtoGet), HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<List<Post>> getPosts(
            BigDecimal id, String tagId, String tagName, String authorName,
            String sort, Integer pageNum, Integer pageSize) {

        var postDtoPageGet = postService.getPosts(
                id, tagId, tagName, authorName,
                sort, pageNum, pageSize);

        var postPageGet = postMapper.toPostPage(postDtoPageGet);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(postPageGet);
        return new ResponseEntity<>(postPageGet.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('role:moderator-admin')")
    public ResponseEntity<Void> removeComment(BigDecimal postId, BigDecimal id) {
        commentService.removeComment(postId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAuthority('role:moderator-admin')")
    public ResponseEntity<Void> removePost(BigDecimal id) {
        postService.removePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAuthority('role:moderator-admin')")
    public ResponseEntity<Comment> updateComment(BigDecimal postId, BigDecimal id, Comment comment) {
        var commentDtoSet = commentMapper.toCommentDto(comment);
        var commentDtoGet = commentService.updateComment(postId, id, commentDtoSet);
        return new ResponseEntity<>(commentMapper.toComment(commentDtoGet), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('role:moderator-admin')")
    public ResponseEntity<Post> updatePost(BigDecimal id, Post post) {
        var postDtoSet = postMapper.toPostDto(post);
        var postDtoGet = postService.updatePost(id, postDtoSet);
        return new ResponseEntity<>(postMapper.toPost(postDtoGet), HttpStatus.OK);
    }
}
