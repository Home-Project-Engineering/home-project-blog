package com.homeproject.blog.backend.business.services;

import com.homeproject.blog.backend.business.models.DTO.PostDTO;

import java.util.Collection;
import java.util.Map;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostDTO updatePost(Long id, PostDTO postDTOUpdate);

    PostDTO readPost(Long id);

    void deletePost(Long id);

    Collection<PostDTO> getPosts(Map<String, String> parameters);
}
