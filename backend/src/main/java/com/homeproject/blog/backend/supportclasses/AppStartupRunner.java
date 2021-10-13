package com.homeproject.blog.backend.supportclasses;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.data.entity.RoleTypeEntity;
import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.data.entity.converters.AuthorConverter;
import com.homeproject.blog.backend.data.repository.RoleTypeRepository;
import com.homeproject.blog.backend.data.repository.UserRepository;
import com.homeproject.blog.backend.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.ArrayList;


@Component
public class AppStartupRunner implements ApplicationRunner {
    private final Logger LOG = LoggerFactory.getLogger(AppStartupRunner.class);
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RoleTypeRepository roleTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorConverter authorConverter;
    public static UserEntity userEntity;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag(null, "Java8"));
        String date = CurrentDate.getDate();
        RoleType role = new RoleType(null, "client");
        RoleTypeEntity roleEntity = new RoleTypeEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());
        roleEntity = roleTypeRepository.save(roleEntity);
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        User user = new User(null,"Paul", "Pavlo", "Ponomarenko", "reactor", "ds", role);
        userEntity = userRepository.save(authorConverter.authorToEntity(user));
        Author author = new Author(userEntity.getId(),userEntity.getName(), userEntity.getFirstName(), userEntity.getSecondName());
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
