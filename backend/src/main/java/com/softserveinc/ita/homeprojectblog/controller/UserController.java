package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperController;
import com.softserveinc.ita.homeprojectblog.exception.NoSuchUserException;
import com.softserveinc.ita.homeprojectblog.exception.NoSuchUsersException;
import com.softserveinc.ita.homeprojectblog.api.UsersApi;
import com.softserveinc.ita.homeprojectblog.model.Comment;
import com.softserveinc.ita.homeprojectblog.model.Post;
import com.softserveinc.ita.homeprojectblog.model.User;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
    UserMapperController userMapperController;
    NativeWebRequest request;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override // +
//    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<User> createUser(User body) {
        var userDtoSet = userMapperController.toUserDto(body);
        var userDtoGet = userService.createUser(userDtoSet);
        return new ResponseEntity<>(userMapperController.toUser(userDtoGet), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(BigDecimal id, String sort, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<User> getCurrentUser() {
        return new ResponseEntity<>(userMapperController.toUser(userService.getCurrentUser()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Post>> getPostsByCurrentUser(BigDecimal id, String tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override // +
    public ResponseEntity<User> getUser(BigDecimal id) {
        var userDto = userService.getUserById(id);

        if (userDto == null) {
            throw new NoSuchUserException("There is no user with ID = " +
                    id + " in Database");
        }

        return new ResponseEntity<>(userMapperController.toUser(userDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:management')")
    @Override // +
    public ResponseEntity<List<User>> getUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {

        Page<UserDto> userDtoPage = userService.getAllUsers(
                id,
                name,
                sort, // UserAPI set default
                Optional.ofNullable(pageNum).orElse(1),
                Optional.ofNullable(pageSize).orElse(10));

        Page<User> userPage = userMapperController.toUserPage(userDtoPage);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(userPage.getTotalElements()));

        if (userPage.getTotalElements() == 0) {
            throw new NoSuchUsersException("There are no users for your request");
        }

        return new ResponseEntity<>(userPage.getContent(), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        return null;
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

    @Override
    public ResponseEntity<User> updateUser(BigDecimal id, User user) {
        var userDtoSet = userMapperController.toUserDto(user);
        var userDtoGet = userService.updateUser(userDtoSet, id);

        return new ResponseEntity<>(userMapperController.toUser(userDtoGet), HttpStatus.OK);
    }
}
