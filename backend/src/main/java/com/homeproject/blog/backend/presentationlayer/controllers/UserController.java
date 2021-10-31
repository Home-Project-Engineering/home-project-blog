package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.businesslayer.UserService;
import com.homeproject.blog.backend.dtos.RoleType;
import com.homeproject.blog.backend.presentationlayer.converters.CommentConverter;
import com.homeproject.blog.backend.presentationlayer.converters.PostConverter;
import com.homeproject.blog.backend.presentationlayer.converters.UserConverter;
import com.homeproject.blog.backend.presentationlayer.model.*;
import com.homeproject.blog.backend.security.SecurityService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController implements UsersApi {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private PostService postService;

    @Override
    public ResponseEntity<User> createUser(User user) {
        com.homeproject.blog.backend.dtos.User newUser = userService.save(userConverter.modelToDto(user));
        return new ResponseEntity<>(userConverter.dtoToModel(newUser), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        com.homeproject.blog.backend.dtos.User user = userService.findById(id.longValue());
        return new ResponseEntity<>(userConverter.dtoToModel(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @Override
    public ResponseEntity<List<User>> getUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        Long postId = id == null ? null : id.longValue();
        Page<com.homeproject.blog.backend.dtos.User> page = userService.findAll(postId, name, pageNum, pageSize, sort);
        List<User> resultList = userConverter.dtosToViews(page.toList());
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(resultList);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<User> getCurrentUser() {
        com.homeproject.blog.backend.dtos.User dto = securityService.findLoggedInUser();
        User model = userConverter.dtoToModel(dto);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @Override
    public ResponseEntity<Void> removeUser(BigDecimal id) {
        userService.deleteUser(id.longValue());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @Override
    public ResponseEntity<User> updateUser(BigDecimal id, User user) {
        User updatedUser = userConverter.dtoToModel(userService.updateUser(id.longValue(), userConverter.modelToDto(user)));
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @Override
    public ResponseEntity<Role> getUserRole(BigDecimal id) {
        RoleType roleType = userService.getUserRole(id.longValue());
        Role role = new Role();
        return new ResponseEntity<>(role.name(Role.NameEnum.fromValue(roleType.getName())), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Role> updateUserRole(BigDecimal id, Role role) {
        RoleType roleType = new RoleType();
        roleType.setName(role.getName().name());
        RoleType updatedRole = userService.updateUserRole(id.longValue(), roleType);
        return new ResponseEntity<>(role.name(Role.NameEnum.fromValue(updatedRole.getName())), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<User> updateCurrentUser(User user) {
        com.homeproject.blog.backend.dtos.User updaredUser = securityService.updateLoggedInUser(userConverter.modelToDto(user));
        return new ResponseEntity<>(userConverter.dtoToModel(updaredUser), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Void> updateCurrentUserPassword(ChangePassword changePassword) {
        securityService.changePassword(new com.homeproject.blog.backend.dtos.ChangePassword(changePassword.getOldPassword(), changePassword.getNewPassword()));
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        com.homeproject.blog.backend.dtos.Comment comment = securityService.getLoggedInUserComment(id.longValue());
        return new ResponseEntity<>(commentConverter.dtoToView(comment), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(BigDecimal id, String sort, Integer pageNum, Integer pageSize) {
        Long commentId = id == null ? null : id.longValue();
        Page<com.homeproject.blog.backend.dtos.Comment> comments = securityService.getLoggedInUserComments(commentId, sort, pageNum, pageSize);
        return new ResponseEntity<>(comments.stream().map(commentConverter::dtoToView).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        com.homeproject.blog.backend.dtos.Post post = securityService.getLoggedInUserPost(id.longValue());
        return new ResponseEntity<>(postConverter.dtoToView(post), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<List<Post>> getPostsByCurrentUser(BigDecimal id, String tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        Long postId = id == null ? null : id.longValue();
        Page<com.homeproject.blog.backend.dtos.Post> posts = securityService.getLoggedInUserPosts(postId, tagId, tagName, sort, pageNum, pageSize);
        return new ResponseEntity<>(posts.stream().map(postConverter::dtoToView).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Comment> updateCommentByCurrentUser(BigDecimal id, Comment comment) {
        securityService.getLoggedInUserComment(id.longValue());
        com.homeproject.blog.backend.dtos.Comment updatedComment = commentService.updateComment(id.longValue(), commentConverter.viewToDTO(comment));
        return new ResponseEntity<>(commentConverter.dtoToView(updatedComment), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Post> updatePostByCurrentUser(BigDecimal id, Post post) {
        securityService.getLoggedInUserPost(id.longValue());
        com.homeproject.blog.backend.dtos.Post updatedPost = postService.updatePost(id.longValue(), postConverter.viewToDTO(post));
        return new ResponseEntity<>(postConverter.dtoToView(updatedPost), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        securityService.getLoggedInUserComment(id.longValue());
        commentService.deleteComment(id.longValue(), null);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    @Override
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        securityService.getLoggedInUserPost(id.longValue());
        postService.deletePost(id.longValue());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}