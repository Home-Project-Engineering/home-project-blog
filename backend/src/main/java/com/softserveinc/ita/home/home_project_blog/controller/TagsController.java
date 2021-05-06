package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewTagDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.TagMapperController;
import com.softserveinc.ita.home.home_project_blog.service.GeneralService;
import com.softserveinc.ita.home.home_project_blog.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/1/tags", produces = "application/json")
public class TagsController {
    private final ITagService postService;
    private final TagMapperController mapper;
    private final GeneralService<ViewTagDto> generalService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ViewTagDto>> getAllTags(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        return generalService.toResponseEntity(mapper.toPageViewTagDto(
                postService.findAll(id, name, page_num, page_size, sort)));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewTagDto> getTagById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mapper.toViewTagDto(postService.getById(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('tags:delete')")
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewTagDto> deleteTag(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
