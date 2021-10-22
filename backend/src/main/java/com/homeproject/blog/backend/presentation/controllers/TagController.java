package com.homeproject.blog.backend.presentation.controllers;

import com.homeproject.blog.backend.business.models.DTO.Tag;
import com.homeproject.blog.backend.business.services.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class TagController {
    @Autowired
    private TagService tagService;
    private static final Logger LOG = LoggerFactory.getLogger(TagController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllTags() {
        LOG.info("Get all tags request");
        Collection<Tag> comments = tagService.getTags();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getTagById(@PathVariable Long id) {
        LOG.info("Get tag by id request");
            Tag tag = tagService.readTag(id);
            return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        LOG.info("Delete post request");
        tagService.deleteTag(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
