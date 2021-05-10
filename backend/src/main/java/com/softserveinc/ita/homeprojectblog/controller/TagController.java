package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.api.TagsApi;
import com.softserveinc.ita.homeprojectblog.mapper.TagMapperController;
import com.softserveinc.ita.homeprojectblog.model.Tag;
import com.softserveinc.ita.homeprojectblog.service.TagService;
import com.softserveinc.ita.homeprojectblog.util.Boilerplate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class TagController implements TagsApi {

    NativeWebRequest request;

    TagService tagService;
    TagMapperController tagMapper;

    Boilerplate boilerplate;

    @Override // +/-
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override // +
    public ResponseEntity<Tag> getTag(BigDecimal id) {
        var tagDto = tagService.getTag(id);
        return new ResponseEntity<>(tagMapper.toTag(tagDto), HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<List<Tag>> getTags(
            BigDecimal id, String name,
            String sort, Integer pageNum, Integer pageSize) {

        var tagDtoPage = tagService.getTags(id, name, sort, pageNum, pageSize);
        var tagPage = tagMapper.toTagPage(tagDtoPage);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(tagPage);
        return new ResponseEntity<>(tagPage.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    public ResponseEntity<Void> removeTag(BigDecimal id) {
        tagService.removeTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
