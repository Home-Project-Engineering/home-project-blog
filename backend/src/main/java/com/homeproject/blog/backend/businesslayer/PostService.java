package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.entities.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;

import java.util.Collection;
import java.util.Map;

public interface PostService {

    Post createPost(Post post) throws TagNotFoundException;

    Post updatePost(Long id, Post changes) throws PostNotFoundException, TagNotFoundException;

    Post readPost(Long id) throws PostNotFoundException;

    Collection<Post> getPosts();

    Collection<Post> sortPosts(Collection<Post> posts, Map<String, String> parameters);

    void deletePost(Long id) throws PostNotFoundException;
}
