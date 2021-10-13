package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.TagService;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.ForbiddenActionException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;
    private final Logger LOG = LoggerFactory.getLogger(TagController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllTags(@RequestParam( name = "id", required = false) Long id, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "sort", required = false, defaultValue = "name") String sort, @RequestParam(name = "page_num", required = false, defaultValue = "0") Integer pageNum, @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer pageSize) {
        LOG.info("Get all tags request");
        Page<Tag> page = tagService.findAll(id, name, pageNum, pageSize, sort);
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.toList());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getTagById(@PathVariable Long id) throws TagNotFoundException {
        LOG.info("Get tag by id request");
        Tag tag = tagService.readTag(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deleteTag(@PathVariable Long id) throws TagNotFoundException, ForbiddenActionException {
        LOG.info("Delete post request");
            tagService.deleteTag(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
