package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.api.UsersApi;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperController;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperController;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperController;
import com.softserveinc.ita.homeprojectblog.model.*;
import com.softserveinc.ita.homeprojectblog.service.CommentService;
import com.softserveinc.ita.homeprojectblog.service.PostService;
import com.softserveinc.ita.homeprojectblog.service.UserService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class UserController implements UsersApi {

    UserService userService;

    UserMapperController userMapper;
    CommentMapperController commentMapper;
    PostMapperController postMapper;

    NativeWebRequest request;

    CommentService commentService;
    PostService postService;

    Boilerplate boilerplate;

    @Override // +/-
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override // +
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<User> createUser(User body) {
        var userDtoSet = userMapper.toUserDto(body);
        var userDtoGet = userService.createUser(userDtoSet);
        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.CREATED);
    }

    @Override // +
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        var commentDto = commentService.getCommentByCurrentUser(id);
        return new ResponseEntity<>(commentMapper.toComment(commentDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(
            BigDecimal id,
            String sort, Integer pageNum, Integer pageSize) {

        var commentDtoPage = commentService.getCommentsByCurrentUser(id, sort, pageNum, pageSize);
        var commentPage = commentMapper.toCommentPage(commentDtoPage);

        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(commentPage);

        return new ResponseEntity<>(commentPage.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<User> getCurrentUser() {
        var userDto = userService.getCurrentUser();
        return new ResponseEntity<>(userMapper.toUser(userDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        var postDto = postService.getPostByCurrentUser(id);
        return new ResponseEntity<>(postMapper.toPost(postDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<List<Post>> getPostsByCurrentUser(
            BigDecimal id, String tagId, String tagName,
            String sort, Integer pageNum, Integer pageSize) {

        var postDtoPage = postService.getPostsByCurrentUser(id, tagId, tagName, sort, pageNum, pageSize);

        var postPage = postMapper.toPostPage(postDtoPage);

        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(postPage);

        return new ResponseEntity<>(postPage.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<User> getUser(BigDecimal id) {
        var userDto = userService.getUser(id);
        return new ResponseEntity<>(userMapper.toUser(userDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:management')")
    @Override // +
    public ResponseEntity<List<User>> getUsers(BigDecimal id, String name,
                                               String sort, Integer pageNum, Integer pageSize) {

        var userDtoPage = userService.getUsers(id, name, sort, pageNum, pageSize);

        var userPage = userMapper.toUserPage(userDtoPage);

        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(userPage);

        return new ResponseEntity<>(userPage.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        commentService.removeCommentByCurrentUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        postService.removePostByCurrentUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    public ResponseEntity<Void> removeUser(BigDecimal id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Comment> updateCommentByCurrentUser(BigDecimal id, Comment comment) {
        return UsersApi.super.updateCommentByCurrentUser(id, comment);
    }

    @Override
    public ResponseEntity<User> updateCurrentUser(User body) {
        return null;
    }

    @Override
    public ResponseEntity<Post> updatePostByCurrentUser(BigDecimal id, Post post) {
        return UsersApi.super.updatePostByCurrentUser(id, post);
    }

    @Override // +
    public ResponseEntity<User> updateUser(BigDecimal id, User user) {
        var userDtoSet = userMapper.toUserDto(user);
        var userDtoGet = userService.updateUser(userDtoSet, id);

        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Role> getUserRole(BigDecimal id) {
        return UsersApi.super.getUserRole(id);
    }

    @Override
    public ResponseEntity<Void> updateCurrentUserPassword(Password password) {
        return UsersApi.super.updateCurrentUserPassword(password);
    }

    @Override
    public ResponseEntity<Role> updateUserRole(BigDecimal id, Role role) {
        return UsersApi.super.updateUserRole(id, role);
    }
}
