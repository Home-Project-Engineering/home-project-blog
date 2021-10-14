package com.homeproject.blog.backend.services;

import com.homeproject.blog.backend.classes.Post;

import java.util.Collection;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Long id, Post postUpdate);

    Post readPost(Long id);

    Collection<Post> getPosts();

    void deletePost(Long id);

    Post getPost();
}
