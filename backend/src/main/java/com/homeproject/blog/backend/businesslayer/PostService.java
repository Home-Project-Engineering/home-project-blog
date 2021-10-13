package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Long id, Post changes) throws PostNotFoundException;

    Post readPost(Long id) throws PostNotFoundException;

    Page<Post> getPosts(Map<String, String> parameters);

    void deletePost(Long id) throws PostNotFoundException;
}
