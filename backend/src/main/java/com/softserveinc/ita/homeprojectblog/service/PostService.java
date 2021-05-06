package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
