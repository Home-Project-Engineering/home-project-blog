package com.homeproject.blog.backend.security;

import com.homeproject.blog.backend.dtos.ChangePassword;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.dtos.User;
import org.springframework.data.domain.Page;

public interface SecurityService {
    User findLoggedInUser();

    Long getLoggedInUserId();

    Comment getLoggedInUserComment(Long commentId);

    Post getLoggedInUserPost(Long postId);

    Page<Comment> getLoggedInUserComments(Long commentId, String sort, Integer pageNum, Integer pageSize);

    Page<Post> getLoggedInUserPosts(Long postId, String tagId, String tagName, String sort, Integer pageNum, Integer pageSize);

    User updateLoggedInUser(User user);

    void changePassword(ChangePassword password);
}
