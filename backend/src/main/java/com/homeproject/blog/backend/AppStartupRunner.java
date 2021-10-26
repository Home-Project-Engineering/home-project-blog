package com.homeproject.blog.backend;

import com.homeproject.blog.backend.data.entities.*;
import com.homeproject.blog.backend.data.repositories.CommentRepository;
import com.homeproject.blog.backend.data.repositories.PostRepository;
import com.homeproject.blog.backend.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.homeproject.blog.backend.data.entities.Role.RoleName.ADMIN;


@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = new Role();
        role.setName(ADMIN);
        roleRepository.save(role);
        for (int i = 8; i >= 5; i--) {
            Post newPost = new Post();
            Tag tag1 = new Tag();
            List<Tag> tagList = new ArrayList<>();
            tag1.setName("Java" + i);
            tagList.add(tag1);
            Tag tag2 = new Tag();
            tag2.setName("Java" + i + 100);
            tagList.add(tag2);
            newPost.setTags(tagList);
            User user = new User();
            user.setName("Ivan23" + i);
            user.setFirstName("Ivan");
            user.setLastName("Nefedov");
            user.setEmail("email@test.com");
            user.setPassword("4s56fr4g56" + i);
            user.setRole(role);
            newPost.setAuthor(user);
            newPost.setCreatedOn(OffsetDateTime.now());
            newPost.setText("Text" + i);
            newPost.setTitle("Title" + i);
            newPost.setPreviewAttachment("String" + i);
            newPost.setUpdatedOn(OffsetDateTime.now());
            Comment comment = new Comment();
            comment.setAuthor(user);
            comment.setText("Comment");
            comment.setPost(newPost);
            comment.setCreatedOn(OffsetDateTime.now());
            comment.setUpdatedOn(OffsetDateTime.now());
            commentRepository.save(comment);
            postRepository.save(newPost);
        }
    }
}
