package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreatePostDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewPostDto;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.PostMapperController;
import com.softserveinc.ita.home.home_project_blog.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/1/posts", produces = "application/json")
public class PostsController {
    private final IPostService postService;
    private final PostMapperController mapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ViewPostDto>> getAllPosts(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        Page<ViewPostDto> pagedResult = mapper.toPageViewPostDto(
                postService.findAll(id, title, page_num, page_size, sort));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(pagedResult.getTotalElements()));
        return new ResponseEntity<>(pagedResult.getContent(), responseHeaders, HttpStatus.OK);
    }

    //    @PreAuthorize("hasAuthority('posts:read')")
//    @GetMapping(path = "/{id}", produces = "application/json")
//    public ResponseEntity<ViewPostDto> getPostById(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(mapper.toViewPostDto(postService.getById(id)), HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/current", produces = "application/json")
//    public ResponseEntity<ViewPostDto> getCurrentPost() {
//        return new ResponseEntity<>(mapper.toViewPostDto(postService.getCurrentPost()), HttpStatus.OK);
//    }
//
//    @PutMapping(path = "/current", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<ViewPostDto> updateCurrentPost(@Valid @RequestBody UpdatePostDto post) {
//        return new ResponseEntity<>(mapper.toViewPostDto(postService.updateCurrentPost(mapper.UpdateToPostDto(post))), HttpStatus.OK);
//    }
    @PreAuthorize("hasAuthority('posts:create')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ViewPostDto> createPost(@Valid @RequestBody CreatePostDto post) {
        return new ResponseEntity<>(mapper.toViewPostDto(postService.save(mapper.toPostDto(post))), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAuthority('posts:write')")
//    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<ViewPostDto> updatePost(@PathVariable Long id,
//                                                  @Valid @RequestBody UpdatePostDto post) {
//        return new ResponseEntity<>(mapper.toViewPostDto(postService.update(id, mapper.UpdateToPostDto(post))), HttpStatus.OK);
//    }
//
//    @PreAuthorize("hasAuthority('posts:write')")
//    @DeleteMapping(path = "/{id}", produces = "application/json")
//    public ResponseEntity<ViewPostDto> deletePost(@PathVariable Long id) {
//        postService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
