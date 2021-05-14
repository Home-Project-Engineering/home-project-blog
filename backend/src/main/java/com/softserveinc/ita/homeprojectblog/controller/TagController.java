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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.security.PermitAll;
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
    @PermitAll
    public ResponseEntity<Tag> getTag(BigDecimal id) {
        var tagDtoGet = tagService.getTag(id);
        return new ResponseEntity<>(tagMapper.toTag(tagDtoGet), HttpStatus.OK);
    }

    @Override // +
    @PermitAll
    public ResponseEntity<List<Tag>> getTags(
            BigDecimal id, String name,
            String sort, Integer pageNum, Integer pageSize) {

        var tagDtoPageGet = tagService.getTags(id, name, sort, pageNum, pageSize);
        var tagPageGet = tagMapper.toTagPage(tagDtoPageGet);
        MultiValueMap<String, String> headers = boilerplate.getXTotalCount(tagPageGet);
        return new ResponseEntity<>(tagPageGet.getContent(), headers, HttpStatus.OK);
    }

    @Override // +
    @PreAuthorize("hasAuthority('role:moderator-admin')")
    public ResponseEntity<Void> removeTag(BigDecimal id) {
        tagService.removeTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
