package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface IPostService {
    Page<PostDto> findAll(Long id, String title, Integer pageNum, Integer pageSize, String sort);

    PostDto getById(Long id);

    PostDto save(@Valid PostDto post);

//    PostDto update(Long id, @Valid PostDto post);
//
//    void delete(Long id);
}
