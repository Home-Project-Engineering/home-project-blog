package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.TagService;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.ForbiddenActionException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import com.homeproject.blog.backend.presentationlayer.converters.TagConverter;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


@Controller
public class TagController implements TagsApi {
    @Autowired
    private TagService tagService;
    @Autowired
    private TagConverter tagConverter;
    private final Logger LOG = LoggerFactory.getLogger(TagController.class);

    @Override
    public ResponseEntity<List<com.homeproject.blog.backend.presentationlayer.model.Tag>> getTags(Long id, String name, String sort, Integer pageNum, Integer pageSize) {
        LOG.info("Get all tags request");
        Page<Tag> page = tagService.findAll(id, name, pageNum, pageSize, sort);
        List<com.homeproject.blog.backend.presentationlayer.model.Tag> resultList = tagConverter.dtosToViews(page.toList());
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(resultList);
    }

    @Override
    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Tag> getTag(Long id) {
        LOG.info("Get tag by id request");
        Tag tag = tagService.readTag(id);
        return new ResponseEntity<>(tagConverter.dtoToView(tag), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'moderator')")
    @Override
    public ResponseEntity<Void> removeTag(Long id) {
        LOG.info("Delete post request");
        tagService.deleteTag(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}