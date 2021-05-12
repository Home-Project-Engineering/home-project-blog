package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.*;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.CommentMapperController;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.PostMapperController;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.UserMapperController;
import com.softserveinc.ita.home.home_project_blog.service.GeneralService;
import com.softserveinc.ita.home.home_project_blog.service.ICommentService;
import com.softserveinc.ita.home.home_project_blog.service.IPostService;
import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/1/users/current", produces = "application/json")
public class CurrentUserController {
    private final IUserService userService;
    private final IPostService postService;
    private final ICommentService commentService;
    private final GeneralService<ViewPostDto> generalPostService;
    private final GeneralService<ViewCommentDto> generalCommentService;
    private final UserMapperController userMapper;
    private final PostMapperController postMapper;
    private final CommentMapperController commentMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<ViewUserDto> getCurrentUser() {
        return new ResponseEntity<>(userMapper.toViewUserDto(userService.getCurrentUser()), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewUserDto> updateCurrentUser(@Valid @RequestBody UpdateUserDto user) {
        return new ResponseEntity<>(userMapper.toViewUserDto(userService.updateCurrentUser(userMapper.UpdateToUserDto(user))), HttpStatus.OK);
    }

    @GetMapping(path = "/posts", produces = "application/json")
    public ResponseEntity<List<ViewPostDto>> getPostsByCurrentUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long tag_id,
            @RequestParam(required = false) String tag_name,
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        return generalPostService.toResponseEntity(postMapper.toPageViewPostDto(
                postService.getPostsByCurrentUser(id, tag_id, tag_name, page_num, page_size, sort)));
    }

    @GetMapping(path = "/posts/{post_id}", produces = "application/json")
    public ResponseEntity<ViewPostDto> getPostByCurrentUser(@PathVariable("post_id") Long post_id) {
        return new ResponseEntity<>(postMapper.toViewPostDto(postService.getPostByIdByCurrentUser(post_id)), HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{post_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewPostDto> updatePostByCurrentUser(@PathVariable("post_id") Long post_id, @Valid @RequestBody CreatePostDto post) {
        return new ResponseEntity<>(postMapper.toViewPostDto(postService.updatePostByCurrentUser(post_id, postMapper.toPostDto(post))), HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{post_id}", produces = "application/json")
    public ResponseEntity<ViewPostDto> deletePostByCurrentUser(@PathVariable("post_id") Long post_id) {
        postService.deletePostByCurrentUser(post_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/comments", produces = "application/json")
    public ResponseEntity<List<ViewCommentDto>> getAllComments(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        return generalCommentService.toResponseEntity(commentMapper.toPageViewCommentDto(
                commentService.findAll(null, id, null, userService.getCurrentUser().getId(), page_num, page_size, sort)));
    }

    @GetMapping(path = "/comments/{id}", produces = "application/json")
    public ResponseEntity<ViewCommentDto> getCommentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentMapper.toViewCommentDto(commentService.getCommentByIdByCurrentUser(id)), HttpStatus.OK);
    }

    @PutMapping(path = "/comments/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewCommentDto> updateComment(@PathVariable Long id,
                                                        @Valid @RequestBody CreateCommentDto comment) {
        return new ResponseEntity<>(
                commentMapper.toViewCommentDto(
                commentService.updateCommentByCurrentUser(id, commentMapper.toCommentDto(comment))),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/comments/{id}", produces = "application/json")
    public ResponseEntity<ViewCommentDto> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentByCurrentUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
