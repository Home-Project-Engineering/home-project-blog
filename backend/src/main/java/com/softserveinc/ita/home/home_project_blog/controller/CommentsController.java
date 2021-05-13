package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreateCommentDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewCommentDto;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.CommentMapperController;
import com.softserveinc.ita.home.home_project_blog.service.GeneralService;
import com.softserveinc.ita.home.home_project_blog.service.ICommentService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "/api/1/posts/{post_id}/comments", produces = "application/json")
public class CommentsController {
    private final ICommentService commentService;
    private final CommentMapperController mapper;
    private final GeneralService<ViewCommentDto> generalService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ViewCommentDto>> getAllComments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String user_name,
            @RequestParam(required = false) Long user_id,
            @RequestParam(defaultValue = "1") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort,
            @PathVariable("post_id") Long post_id
    ) {
        return generalService.toResponseEntity(mapper.toPageViewCommentDto(
                commentService.findAll(post_id, id, user_name, user_id, page_num, page_size, sort)));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewCommentDto> getCommentById(@PathVariable("id") Long id, @PathVariable("post_id") Long post_id) {
        return new ResponseEntity<>(mapper.toViewCommentDto(commentService.getById(post_id, id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('comments:create')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ViewCommentDto> createComment(@Valid @RequestBody CreateCommentDto comment, @PathVariable("post_id") Long post_id) {
        return new ResponseEntity<>(mapper.toViewCommentDto(commentService.createComment(post_id, mapper.toCommentDto(comment))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('comments:update:delete')")
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewCommentDto> updateComment(@PathVariable Long id, @PathVariable("post_id") Long post_id,
                                                        @Valid @RequestBody CreateCommentDto comment) {
        return new ResponseEntity<>(mapper.toViewCommentDto(commentService.update(post_id, id, mapper.toCommentDto(comment))), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('comments:update:delete')")
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewCommentDto> deleteComment(@PathVariable Long id, @PathVariable("post_id") Long post_id) {
        commentService.delete(post_id, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
