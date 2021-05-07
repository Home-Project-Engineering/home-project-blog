package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;

import java.math.BigDecimal;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPost(BigDecimal id);
}
