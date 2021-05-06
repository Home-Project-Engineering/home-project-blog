package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreatePostDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.UpdateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewPostDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.PostMapperController;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.UserMapperController;
import com.softserveinc.ita.home.home_project_blog.service.ICurrentUserService;
import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
    private final ICurrentUserService currentUserService;
    private final UserMapperController userMapper;
    private final PostMapperController postMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<ViewUserDto> getCurrentUser() {
        return new ResponseEntity<>(userMapper.toViewUserDto(currentUserService.getCurrentUser()), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewUserDto> updateCurrentUser(@Valid @RequestBody UpdateUserDto user) {
        return new ResponseEntity<>(userMapper.toViewUserDto(currentUserService.updateCurrentUser(userMapper.UpdateToUserDto(user))), HttpStatus.OK);
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
        Page<ViewPostDto> pagedResult = postMapper.toPageViewPostDto(
                currentUserService.getPosts(id, tag_id, tag_name, page_num, page_size, sort));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(pagedResult.getTotalElements()));
        return new ResponseEntity<>(pagedResult.getContent(), responseHeaders, HttpStatus.OK);

//        return new ResponseEntity<>(userMapper.toViewUserDto(userService.getCurrentUser()), HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{post_id}", produces = "application/json")
    public ResponseEntity<ViewPostDto> getPostByCurrentUser(@PathVariable("post_id") Long post_id) {
        return new ResponseEntity<>(postMapper.toViewPostDto(currentUserService.getPostById(post_id)), HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{post_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewPostDto> updatePostByCurrentUser(@PathVariable("post_id") Long post_id,@Valid @RequestBody CreatePostDto post) {
        return new ResponseEntity<>(postMapper.toViewPostDto(currentUserService.updatePost(post_id, postMapper.toPostDto(post))), HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{post_id}", produces = "application/json")
    public ResponseEntity<ViewPostDto> deletePostByCurrentUser(@PathVariable("post_id") Long post_id) {
        currentUserService.deletePost(post_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
