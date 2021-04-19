package com.itacademy.blog.services.service;

import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.services.DTO.PostDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
public interface PostService{
    PostDTO createPost(PostDTO createPostDto);

    PostDTO updatePost(Long id, PostDTO updatePostDto);

    List<PostDTO> findPosts(Integer pageNumber, Integer pageSize, String sort, Specification<Post> specification);

    PostDTO getPostById(Long id);

    PostDTO deletePost(Long id);
}
