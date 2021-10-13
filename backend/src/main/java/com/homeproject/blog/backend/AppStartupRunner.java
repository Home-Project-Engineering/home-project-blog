package com.homeproject.blog.backend;

import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Tag;
import com.homeproject.blog.backend.data.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 8; i >= 3; i--) {
            Post newPost = new Post();
            Tag tag = new Tag();
            List<Tag> tagList = new ArrayList<>();
            tag.setName("Java" + i);
            tagList.add(tag);
            newPost.setTags(tagList);
            newPost.setCreatedOn("2021/10/06 17:07:3" + i);
            newPost.setText("Text" + i);
            newPost.setTitle("Title" + i);
            newPost.setPreviewAttachment("String" + i);
            newPost.setUpdatedOn("2021/10/06 17:07:3" + i);
            postRepository.save(newPost);
        }

    }
}
