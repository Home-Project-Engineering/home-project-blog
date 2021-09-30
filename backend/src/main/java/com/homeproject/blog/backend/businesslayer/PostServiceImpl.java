package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.classes.Author;
import com.homeproject.blog.backend.classes.Post;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class PostServiceImpl implements PostService {

    private static final Map<Long, Post> posts = new HashMap<>();
    private static final AtomicLong index = new AtomicLong();
    private static Long getNextIndex (){
        return index.getAndIncrement();
    }

    static {
        Author Ana = new Author("Ana", 1);


        populatePost(Ana, "I studied spring today.");
        populatePost(Ana, "Drinking coffee.");
        populatePost(Ana, "Made map.");

    }

    private static void populatePost(Author Ana, String text1) {
        Long nextIndex = getNextIndex();
        Post p1 = new Post(text1, Ana, nextIndex);

        posts.put(nextIndex,p1);
    }


    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Post post) {
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
}
