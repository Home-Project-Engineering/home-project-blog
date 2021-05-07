package com.example.blog.controllers;


import com.example.blog.generated.api.TagsApi;
import com.example.blog.generated.model.Tag;
import com.example.blog.services.TagService;
import com.example.blog.util.dtos.DtoTag;
import com.example.blog.util.mappers.TagMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TagsController implements TagsApi {

    private TagService tagService;

    public TagsController(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public ResponseEntity<Tag> getTag(BigDecimal id) {

        DtoTag dtoTag = tagService.getTag(id.longValue());

        Tag tag = TagMapper.INSTANCE.toModel(dtoTag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Tag>> getTags(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        Page<DtoTag> tagPage;
        if (id != null) {
            tagPage = tagService.getTags(id.longValue(), name, sort, pageNum, pageSize);
        } else {
            tagPage = tagService.getTags(null, name, sort, pageNum, pageSize);
        }

        List<Tag> tags = TagMapper.INSTANCE.toModels(tagPage.getContent());

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("X-Total-Count", String.valueOf(tagPage.getTotalPages()));

        return new ResponseEntity<>(tags, respHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeTag(BigDecimal id) {

        tagService.delete(id.longValue());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
