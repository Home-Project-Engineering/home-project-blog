package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.classes.Post;

import java.util.Collection;
import java.util.List;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Post post);

    Post readPost(Long id);

    Collection<Post> getPosts();

    void deletePost(Long id);
}
