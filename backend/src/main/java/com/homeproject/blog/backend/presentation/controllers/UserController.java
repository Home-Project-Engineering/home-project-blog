package com.homeproject.blog.backend.presentation.controllers;

import com.homeproject.blog.backend.presentation.converters.CommentConverter;
import com.homeproject.blog.backend.presentation.converters.PostConverter;
import com.homeproject.blog.backend.presentation.converters.UserConverter;
import com.homeproject.blog.backend.business.models.DTO.*;
import com.homeproject.blog.backend.business.models.additional.Role;
import com.homeproject.blog.backend.business.services.CommentService;
import com.homeproject.blog.backend.business.services.PostService;
import com.homeproject.blog.backend.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController{
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private PostService postService;

    public ResponseEntity<UserDTO> createUser(UserDTO user) {
        com.homeproject.blog.backend.business.models.DTO.UserDTO newUser = userService.save(userConverter.modelToDto(user));
        return new ResponseEntity<>(userConverter.dtoToModel(newUser), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<UserDTO> getUser(Long id) {
        com.homeproject.blog.backend.business.models.DTO.UserDTO user = userService.findById(id);
        return new ResponseEntity<>(userConverter.dtoToModel(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<UserDTO>> getUsers(Long id, String name, String sort, Integer pageNum, Integer pageSize) {
        Page<com.homeproject.blog.backend.business.models.DTO.UserDTO> page = userService.findAll(id, name, pageNum, pageSize, sort);
        List<UserDTO> resultList = userConverter.dtosToViews(page.toList());
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(resultList);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<UserDTO> getCurrentUser() {
        com.homeproject.blog.backend.business.models.DTO.UserDTO dto = securityService.findLoggedInUser();
        UserDTO model = userConverter.dtoToModel(dto);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> removeUser(Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<UserDTO> updateUser(Long id, UserDTO user) {
        UserDTO updatedUser = userConverter.dtoToModel(userService.updateUser(id, userConverter.modelToDto(user)));
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Role> getUserRole(Long id) {
        RoleTypeDTO roleType = userService.getUserRole(id);
        Role role = new Role();
        return new ResponseEntity<>(role.name(Role.NameEnum.fromValue(roleType.getName())), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Role> updateUserRole(Long id, Role role) {
        RoleTypeDTO roleType = new RoleTypeDTO();
        roleType.setName(role.getName().name());
        RoleTypeDTO updatedRole = userService.updateUserRole(id, roleType);
        return new ResponseEntity<>(role.name(Role.NameEnum.fromValue(updatedRole.getName())), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<UserDTO> updateCurrentUser(UserDTO user) {
        com.homeproject.blog.backend.business.models.DTO.UserDTO updaredUser = securityService.updateLoggedInUser(userConverter.modelToDto(user));
        return new ResponseEntity<>(userConverter.dtoToModel(updaredUser), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<Void> updateCurrentUserPassword(ChangePasswordDTO changePassword) {
        securityService.changePassword(new com.homeproject.blog.backend.business.models.DTO.ChangePasswordDTO(changePassword.getOldPassword(), changePassword.getNewPassword()));
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<CommentDTO> getCommentByCurrentUser(Long id) {
        com.homeproject.blog.backend.business.models.DTO.CommentDTO comment = securityService.getLoggedInUserComment(id);
        return new ResponseEntity<>(commentConverter.dtoToView(comment), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<List<CommentDTO>> getCommentsByCurrentUser(Long id, String sort, Integer pageNum, Integer pageSize) {
        Page<com.homeproject.blog.backend.business.models.DTO.CommentDTO> comments = securityService.getLoggedInUserComments(id, sort, pageNum, pageSize);
        return new ResponseEntity<>(comments.stream().map(commentConverter::dtoToView).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<PostDTO> getPostByCurrentUser(Long id) {
        com.homeproject.blog.backend.business.models.DTO.PostDTO post = securityService.getLoggedInUserPost(id);
        return new ResponseEntity<>(postConverter.dtoToView(post), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<List<PostDTO>> getPostsByCurrentUser(Long id, Long tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        Page<com.homeproject.blog.backend.business.models.DTO.PostDTO> posts = securityService.getLoggedInUserPosts(id, tagId, tagName, sort, pageNum, pageSize);
        return new ResponseEntity<>(posts.stream().map(postConverter::dtoToView).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<CommentDTO> updateCommentByCurrentUser(Long id, CommentDTO comment) {
        securityService.getLoggedInUserComment(id);
        com.homeproject.blog.backend.business.models.DTO.CommentDTO updatedComment = commentService.updateComment(id, commentConverter.viewToDTO(comment));
        return new ResponseEntity<>(commentConverter.dtoToView(updatedComment), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<PostDTO> updatePostByCurrentUser(Long id, Post post) {
        securityService.getLoggedInUserPost(id);
        com.homeproject.blog.backend.business.models.DTO.PostDTO updatedPost = postService.updatePost(id, postConverter.viewToDTO(post));
        return new ResponseEntity<>(postConverter.dtoToView(updatedPost), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<Void> removeCommentByCurrentUser(Long id) {
        securityService.getLoggedInUserComment(id);
        commentService.deleteComment(id, null);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator', 'blogger')")
    public ResponseEntity<Void> removePostByCurrentUser(Long id) {
        securityService.getLoggedInUserPost(id);
        postService.deletePost(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}