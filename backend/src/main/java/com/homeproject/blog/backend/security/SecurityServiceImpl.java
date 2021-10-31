package com.homeproject.blog.backend.security;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.businesslayer.converters.UserConverter;
import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.data.repository.UserRepository;
import com.homeproject.blog.backend.dtos.ChangePassword;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.dtos.User;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;

    @Override
    public User findLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userInfo = (UserDetails) userDetails;
        String userName = userInfo.getUsername();
        UserEntity userEntity = userRepository.findUserByName(userName);
        return userConverter.entityToUser(userEntity);
    }

    @Override
    public Long getLoggedInUserId() {
        User user = findLoggedInUser();
        return user.getId();
    }
    @Override
    public Comment getLoggedInUserComment(Long commentId) {
        User user = findLoggedInUser();
        Page<Comment> page = commentService.findAll(null, commentId, user.getName(), 0, 1, null);
        if (page.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return page.toList().get(0);
    }

    @Override
    public Post getLoggedInUserPost(Long postId) {
        User user = findLoggedInUser();
        Page<Post> page = postService.getPosts(postId, null, null, user.getName(), null, 0, 1);
        if (page.isEmpty()) {
            throw new PostNotFoundException();
        }
        return page.toList().get(0);
    }

    @Override
    public Page<Comment> getLoggedInUserComments(Long commentId, String sort, Integer pageNum, Integer pageSize) {
        User user = findLoggedInUser();
        return commentService.findAll(null, commentId, user.getName(), pageNum, pageSize, sort);
    }

    @Override
    public Page<Post> getLoggedInUserPosts(Long postId, String tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        User user = findLoggedInUser();
        return postService.getPosts(postId, tagId, tagName, user.getName(), sort, pageNum, pageSize);
    }

    @Override
    public User updateLoggedInUser(User user) {
        User loggedIn = findLoggedInUser();
        if (loggedIn == null) {
            return null;
        }
        loggedIn.setName(user.getName());
        loggedIn.setFirstName(user.getFirstName());
        loggedIn.setLastName(user.getLastName());
        loggedIn.setEmail(user.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        loggedIn.setPassword(encoder.encode(user.getPassword()));
        UserEntity userEntity = userRepository.save(userConverter.userToEntity(loggedIn));
        return userConverter.entityToUser(userEntity);
    }

    @Override
    public void changePassword(ChangePassword password) {
        User loggedIn = findLoggedInUser();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        loggedIn.setPassword(encoder.encode(password.getNewPassword()));
        userRepository.save(userConverter.userToEntity(loggedIn));
    }
}
