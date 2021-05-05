package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ITagService {
    Page<TagDto> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort);

    TagDto getById(Long id);
    
    void delete(Long id);
}
