package com.homeproject.blog.backend.impl;

import com.homeproject.blog.backend.repositories.PostRepository;
import com.homeproject.blog.backend.services.PostService;
import com.homeproject.blog.backend.classes.Author;
import com.homeproject.blog.backend.classes.Post;
import com.homeproject.blog.backend.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagService tagService;

    @Override
    public Post createPost(Post post) {
        Post newPost = new Post();
        newPost.setText(post.getText());
        newPost.setAuthor(post.getAuthor().getId());
        newPost.setId(post.getId());
        postRepository.save(newPost);
        

        return null;
    }

    @Override
    public Post updatePost(Long id, Post postUpdate) {
        return null;
    }

    @Override
    public Post readPost(Long id) {
        return null;
    }

    @Override
    public Collection<Post> getPosts() {
        return posts.values();
    }

    @Override
    public void deletePost(Long id) {
    }

    @Override
    public Post getPost() {
        return null;
    }
}
