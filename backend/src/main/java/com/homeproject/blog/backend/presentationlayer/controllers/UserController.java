package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.ChangePasswordDTO;
import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.businesslayer.services.CommentService;
import com.homeproject.blog.backend.businesslayer.services.PostService;
import com.homeproject.blog.backend.businesslayer.services.UserService;
import com.homeproject.blog.backend.presentationlayer.config.ParametersConfig;
import com.homeproject.blog.backend.presentationlayer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserController implements UsersApi {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public ResponseEntity<User> createUser(User user) {
        UserDTO userDTO = userService.createUser(conversionService.convert(user, UserDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(userDTO, User.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(conversionService.convert(userService.getCurrentUser(), User.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> getUser(Long id) {
        return ResponseEntity.ok(conversionService.convert(userService.getUser(id), User.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Role> getUserRole(Long id) {
        return ResponseEntity.ok(conversionService.convert(userService.getUserRole(id), Role.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<User>> getUsers(Long id, String name, String sort, Integer pageNum, Integer pageSize) {
        Page<UserDTO> page = userService.getUsers(id, name, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(user -> conversionService.convert(user, User.class)).collect(Collectors.toList()));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<User> updateCurrentUser(User user) {
        UserDTO userDTO = conversionService.convert(user, UserDTO.class);
        return ResponseEntity.ok(conversionService.convert(userService.updateCurrentUser(userDTO), User.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Void> updateCurrentUserPassword(ChangePassword changePassword) {
        userService.updateCurrentUserPassword(conversionService.convert(changePassword, ChangePasswordDTO.class));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> updateUser(Long id, User user) {
        UserDTO userDTO = userService.updateUser(conversionService.convert(user, UserDTO.class),id);
        return ResponseEntity.ok(conversionService.convert(userDTO, User.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Role> updateUserRole(Long id, Role role) {
        RoleDTO roleDTO = userService.updateUserRole(id, conversionService.convert(role, RoleDTO.class));
        return ResponseEntity.ok(conversionService.convert(roleDTO, Role.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> removeUser(Long id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Comment> getCommentByCurrentUser(Long id) {
        return ResponseEntity.ok(conversionService.convert(commentService.getCommentByCurrentUser(id), Comment.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(Long id, String sort, Integer pageNum, Integer pageSize) {
        Page<CommentDTO> page = commentService.getCommentsByCurrentUser(id, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(comment -> conversionService.convert(comment, Comment.class)).collect(Collectors.toList()));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Comment> updateCommentByCurrentUser(Long id, Comment comment) {
        CommentDTO commentDTO = commentService.updateCommentByCurrentUser(id, conversionService.convert(comment, CommentDTO.class));
        return ResponseEntity.ok(conversionService.convert(commentDTO, Comment.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Void> removeCommentByCurrentUser(Long id) {
        commentService.deleteCommentByCurrentUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Post> getPostByCurrentUser(Long id) {
        return null;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<List<Post>> getPostsByCurrentUser(Long id, Long tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Post> updatePostByCurrentUser(Long id, Post post) {
        return null;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR','BLOGGER')")
    public ResponseEntity<Void> removePostByCurrentUser(Long id) {
        return null;
    }

}

