package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;

import java.util.Collection;
import java.util.Map;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Long id, Post changes) throws PostNotFoundException;

    Post readPost(Long id) throws PostNotFoundException;

    Collection<Post> getPosts(Map<String, String> parameters);

    Collection<Post> sortPosts(Collection<Post> posts, Map<String, String> parameters);

    void deletePost(Long id) throws PostNotFoundException;
}
