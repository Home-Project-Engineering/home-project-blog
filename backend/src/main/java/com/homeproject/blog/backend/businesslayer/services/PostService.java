package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostDTO createPost(PostDTO post);

    PostDTO updatePost(Long id, PostDTO post);

    PostDTO readPost(Long id);

    Page<PostDTO> getPosts(Long id, Long tagId, String tagName, String authorName, Pageable pageRequest);

    void deletePost(Long id);

}
