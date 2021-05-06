package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ICurrentUserService {

    UserDto getCurrentUser();
    UserDto updateCurrentUser(@Valid UserDto user);
    Page<PostDto> getPosts(Long id, Long tag_id, String tag_name, Integer pageNum, Integer pageSize, String sort);

    PostDto getPostById(Long id);
//
//    PostDto save(@Valid PostDto post);
//
    PostDto updatePost(Long post_id, @Valid PostDto post);

    void deletePost(Long id);
}
