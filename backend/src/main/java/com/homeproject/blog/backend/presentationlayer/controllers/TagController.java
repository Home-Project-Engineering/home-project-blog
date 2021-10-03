package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.TagService;
import com.homeproject.blog.backend.entities.Error;
import com.homeproject.blog.backend.entities.Tag;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping(produces = "application/json")
    ResponseEntity<Object> getAllTags() {
        return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<Object> getTagById(@PathVariable Long id) {
        try {
            Tag tag = tagService.readTag(id);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } catch(TagNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        try {
            tagService.deleteTag(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (TagNotFoundException exception) {
            return new ResponseEntity<>(new Error("404", exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
