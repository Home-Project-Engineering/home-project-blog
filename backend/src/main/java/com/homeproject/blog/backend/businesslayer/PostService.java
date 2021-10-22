package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import org.springframework.data.domain.Page;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Long id, Post changes);

    Post readPost(Long id) throws PostNotFoundException;

    Page<Post> getPosts(Long id, String tagId, String tagName, String authorName, String sort, Integer pageNum, Integer pageSize);

    void deletePost(Long id) throws PostNotFoundException;
}
