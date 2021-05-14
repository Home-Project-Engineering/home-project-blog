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

import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class UserController implements UsersApi {

    NativeWebRequest request;

    UserService userService;
    CommentService commentService;
    PostService postService;

    UserMapperController userMapper;
    CommentMapperController commentMapper;
    PostMapperController postMapper;

    Boilerplate boilerplate;

    @Override // +/-
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override // +
    @PermitAll
    public ResponseEntity<User> createUser(User body) {
        var userDtoSet = userMapper.toUserDto(body);
        var userDtoGet = userService.createUser(userDtoSet);
        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.CREATED);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        var commentDtoGet = commentService.getCommentByCurrentUser(id);
        return new ResponseEntity<>(commentMapper.toComment(commentDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(
            BigDecimal id,
            String sort, Integer pageNum, Integer pageSize) {
        var commentDtoPageGet = commentService.getCommentsByCurrentUser(id, sort, pageNum, pageSize);
        var commentPageGet = commentMapper.toCommentPage(commentDtoPageGet);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(commentPageGet);
        return new ResponseEntity<>(commentPageGet.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<User> getCurrentUser() {
        var userDtoGet = userService.getCurrentUser();
        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        var postDtoGet = postService.getPostByCurrentUser(id);
        return new ResponseEntity<>(postMapper.toPost(postDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<List<Post>> getPostsByCurrentUser(
            BigDecimal id, String tagId, String tagName,
            String sort, Integer pageNum, Integer pageSize) {
        var postDtoPageGet = postService.getPostsByCurrentUser(id, tagId, tagName, sort, pageNum, pageSize);
        var postPageGet = postMapper.toPostPage(postDtoPageGet);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(postPageGet);
        return new ResponseEntity<>(postPageGet.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:admin')")
    public ResponseEntity<User> getUser(BigDecimal id) {
        var userDtoGet = userService.getUser(id);
        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:admin')")
    public ResponseEntity<List<User>> getUsers(
            BigDecimal id, String name,
            String sort, Integer pageNum, Integer pageSize) {
        var userDtoPageGet = userService.getUsers(id, name, sort, pageNum, pageSize);
        var userPageGet = userMapper.toUserPage(userDtoPageGet);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(userPageGet);
        return new ResponseEntity<>(userPageGet.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        commentService.removeCommentByCurrentUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        postService.removePostByCurrentUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:admin')")
    public ResponseEntity<Void> removeUser(BigDecimal id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Comment> updateCommentByCurrentUser(BigDecimal id, Comment comment) {
        var commentDtoSet = commentMapper.toCommentDto(comment);
        var commentDtoGet = commentService.updateCommentByCurrentUser(id, commentDtoSet);
        return new ResponseEntity<>(commentMapper.toComment(commentDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<User> updateCurrentUser(User body) {
        var userDtoSet = userMapper.toUserDto(body);
        var userDtoGet = userService.updateCurrentUser(userDtoSet);
        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Post> updatePostByCurrentUser(BigDecimal id, Post post) {
        var postDtoSet = postMapper.toPostDto(post);
        var postDtoGet = postService.updatePostByCurrentUser(id, postDtoSet);
        return new ResponseEntity<>(postMapper.toPost(postDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:admin')")
    public ResponseEntity<User> updateUser(BigDecimal id, User user) {
        var userDtoSet = userMapper.toUserDto(user);
        var userDtoGet = userService.updateUser(userDtoSet, id);
        return new ResponseEntity<>(userMapper.toUser(userDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:admin')")
    public ResponseEntity<Role> getUserRole(BigDecimal id) {
        var roleDtoGet = userService.getUserRole(id);
        return new ResponseEntity<>(userMapper.toRole(roleDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:registered')")
    public ResponseEntity<Void> updateCurrentUserPassword(Password password) {
        var passwordDtoSet = userMapper.toPasswordDto(password);
        userService.updateCurrentUserPassword(passwordDtoSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:admin')")
    public ResponseEntity<Role> updateUserRole(BigDecimal id, Role role) {
        var roleDtoSet = userMapper.toRoleDto(role);
        var roleDtoGet = userService.updateUserRole(id, roleDtoSet);
        return new ResponseEntity<>(userMapper.toRole(roleDtoGet), HttpStatus.OK);
    }
}
