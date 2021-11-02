package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.businesslayer.services.TagService;
import com.homeproject.blog.backend.presentationlayer.config.ParametersConfig;
import com.homeproject.blog.backend.presentationlayer.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TagController implements TagsApi {

    @Autowired
    private TagService tagService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public ResponseEntity<List<Tag>> getTags(Long id, String name, String sort, Integer pageNum, Integer pageSize) {
        Page<TagDTO> page = tagService.getTags(id, name, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(tag -> conversionService.convert(tag, Tag.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Tag> getTag(Long id) {
        return ResponseEntity.ok(conversionService.convert(tagService.getTag(id), Tag.class));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR')")
    public ResponseEntity<Void> removeTag(Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
