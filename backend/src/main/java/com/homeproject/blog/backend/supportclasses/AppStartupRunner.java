package com.homeproject.blog.backend.supportclasses;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.dtos.Author;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.dtos.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class AppStartupRunner implements ApplicationRunner {
    private final Logger LOG = LoggerFactory.getLogger(AppStartupRunner.class);
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag(null, "Java8"));
        String date = CurrentDate.getDate();
        Author author = new Author("Paul", "Pavlo", "Ponomarenko");
        Post post1 = new Post(null, tags, date, author, "Ok", "-", "-", date);
        Post post2 = new Post(null, tags, date, author, "No", "-", "-", date);
        Post post3 = new Post(null, tags, date, author, "Yes", "-", "-", date);
        postService.createPost(post1);
        postService.createPost(post2);
        postService.createPost(post3);
        Comment comment1 = new Comment(null, author, "very good", date, date);
        Comment comment2 = new Comment(null, author, "very bad", date, date);
        Comment comment3 = new Comment(null, author, "normal", date, date);
        commentService.createComment(comment1);
        commentService.createComment(comment2);
        commentService.createComment(comment3);

        LOG.info("The blog is started");
    }
}
