package com.example.blog.controllers;

import com.example.blog.generated.api.UsersApi;
import com.example.blog.generated.model.*;
import com.example.blog.services.PostService;
import com.example.blog.services.UserService;
import com.example.blog.util.dtos.DtoComment;
import com.example.blog.util.dtos.DtoPost;
import com.example.blog.util.dtos.DtoRole;
import com.example.blog.util.dtos.DtoUser;
import com.example.blog.util.mappers.CommentMapper;
import com.example.blog.util.mappers.PostMapper;
import com.example.blog.util.mappers.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class UserController implements UsersApi {

    private UserService userService;
    private PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public ResponseEntity<User> createUser(@Valid User user) {

        DtoUser dto = UserMapper.INSTANCE.fromUser(user);
        DtoUser createdDto = userService.createUser(dto);

        User u = UserMapper.INSTANCE.fromDtoToUser(createdDto);

        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @Override
    @PermitAll
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        DtoComment commentDto = postService.getCommentByCurrentUser(id.longValue());
        Comment comment = CommentMapper.INSTANCE.toModel(commentDto);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(BigDecimal id, String sort, Integer pageNum, Integer pageSize) {

        Page<DtoComment> dtoComments;
        if (id != null) {
            dtoComments = postService.getCommentsByCurrentUser(id.longValue(), sort, pageNum, pageSize);
        } else {
            dtoComments = postService.getCommentsByCurrentUser(null, sort, pageNum, pageSize);
        }

        List<Comment> comments = CommentMapper.INSTANCE.toModels(dtoComments.getContent());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", String.valueOf(dtoComments.getTotalElements()));

        return new ResponseEntity<>(comments, httpHeaders, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<User> getCurrentUser() {
        DtoUser curUser = userService.getCurrentUserDto();
        User user = UserMapper.INSTANCE.fromDtoToUser(curUser);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        DtoPost postDto = userService.getPostByCurrentUser(id.longValue());
        Post post = PostMapper.INSTANCE.toModel(postDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<List<Post>> getPostsByCurrentUser(BigDecimal id, String tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        Page<DtoPost> page;
        if (id == null) {
            page = userService.getPostsByCurrentUser(null, tagId, tagName, sort, pageNum, pageSize);
        } else {
            page = userService.getPostsByCurrentUser(id.longValue(), tagId, tagName, sort, pageNum, pageSize);
        }

        List<Post> posts = PostMapper.INSTANCE.toModels(page.getContent());

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("X-Total-Count", String.valueOf(page.getTotalElements()));


        return new ResponseEntity<>(posts, respHeaders, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('delete:update:posts:comment:tag')")
    public ResponseEntity<User> getUser(BigDecimal id) {
        DtoUser dtoUser = userService.getUser(id.longValue());
        User u = UserMapper.INSTANCE.fromDtoToUser(dtoUser);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('delete:update:posts:comment:tag')")
    public ResponseEntity<List<User>> getUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        Page<DtoUser> page;
        if (id == null) {
            page = userService.getUsers(null, name, sort, pageNum, pageSize);
        } else {
            page = userService.getUsers(id.longValue(), name, sort, pageNum, pageSize);
        }

        List<User> users = UserMapper.INSTANCE.toListUsers(page.getContent());

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("X-Total-Count", String.valueOf(page.getTotalElements()));


        return new ResponseEntity<>(users, respHeaders, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        userService.removeCommentByCurrentUser(id.longValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PermitAll
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        userService.removePostByCurrentUser(id.longValue());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAuthority('delete:update:posts:comment:tag')")
    public ResponseEntity<Role> getUserRole(BigDecimal id) {

        DtoRole dtoRole = userService.getUserRole(id.longValue());
        Role role = UserMapper.INSTANCE.convertToDto(dtoRole);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('delete:update:posts:comment:tag')")
    public ResponseEntity<Role> updateUserRole(BigDecimal id, Role role) {
        DtoRole dtoRole = UserMapper.INSTANCE.convertToDto(role);

        DtoRole updated = userService.updateUserRole(id.longValue(), dtoRole);
        Role updatedRole = UserMapper.INSTANCE.convertToDto(updated);

        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('delete:update:posts:comment:tag')")
    public ResponseEntity<Void> removeUser(BigDecimal id) {
        userService.removeUser(id.longValue());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PermitAll
    public ResponseEntity<Comment> updateCommentByCurrentUser(BigDecimal id, Comment comment) {
        DtoComment dtoComment = CommentMapper.INSTANCE.toDto(comment);
        DtoComment updatedDto = userService
                .updateCommentByCurrentUser(id.longValue(), dtoComment);
        Comment updatedComment = CommentMapper.INSTANCE.toModel(updatedDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<User> updateCurrentUser(User user) {
        DtoUser dtoUser = UserMapper.INSTANCE.fromUser(user);
        DtoUser updatedDto = userService.updateCurrentUser(dtoUser);
        User updatedUser = UserMapper.INSTANCE.fromDtoToUser(updatedDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Void> updateCurrentUserPassword(ChangePassword password) {
        userService.updateCurrentUserPassword(password.getNewPassword(), password.getOldPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> updatePostByCurrentUser(BigDecimal id, Post post) {
        DtoPost postDto = PostMapper.INSTANCE.toDto(post);
        DtoPost updatedDto = userService.updatePostByCurrentUser(id.longValue(), postDto);
        Post updatedPost = PostMapper.INSTANCE.toModel(updatedDto);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('delete:update:posts:comment:tag')")
    public ResponseEntity<User> updateUser(BigDecimal id, User user) {

        DtoUser dtoUser = UserMapper.INSTANCE.fromUser(user);
        DtoUser updateDtoUser = userService.updateUser(id.longValue(), dtoUser);

        User updateUser = UserMapper.INSTANCE.fromDtoToUser(updateDtoUser);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}