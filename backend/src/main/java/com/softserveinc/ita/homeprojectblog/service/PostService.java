package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPost(BigDecimal id);

    Page<PostDto> getPosts(BigDecimal id, String tagId, String tagName, String authorName, String sort, Integer pageNum, Integer pageSize);

    void removePost(BigDecimal id);
}

