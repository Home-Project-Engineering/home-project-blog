package com.itacademy.tests.curentUser;

import com.itacademy.tests.GeneralApi;
import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.CommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.CurrentUserCommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Comment;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentUserCommentsApiIT implements GeneralApi {
    private final PostsApi postsApi = new PostsApi(ApiClientUtil.getAdminClient());
    private final CommentsApi commentsApi = new CommentsApi(ApiClientUtil.getAdminClient());
    private final CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(ApiClientUtil.getAdminClient());

    @Test
    void getCommentByCurrentUser() {
        Post post = postsApi.createPost(createTestPost());
        Comment expected = commentsApi.createComment(post.getId(), new Comment().text(RandomStringUtils.randomAlphabetic(5)));
        Comment actual = currentUserCommentsApi.getCommentByCurrentUser(expected.getId());
        assertComment(expected, actual);

    }

    @Test
    void getCommentsByCurrentUser() {
        Post post = postsApi.createPost(createTestPost());
        saveListComment(post.getId(),commentsApi);
        List<Comment> comments = currentUserCommentsApi.getCommentsByCurrentUser(
                null
                ,"-id"
                ,1
                ,10
        );
        assertThat(comments).isNotEmpty();
    }

    @Test
    void removeCommentByCurrentUser() {
        Post post = postsApi.createPost(createTestPost());
        Comment expected = commentsApi.createComment(post.getId(), createTestComment());
        currentUserCommentsApi.removeCommentByCurrentUser(expected.getId());

        List<Comment> actualCommentsList = commentsApi.getComments(
                post.getId()
                ,expected.getId()
                ,null
                ,"-id"
                , 1
                , 10);

        assertFalse(actualCommentsList.contains(expected));
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> commentsApi.getComment(post.getId(), expected.getId()));
    }

    @Test
    void updateCommentByCurrentUser() {
        Post post = postsApi.createPost(createTestPost());
        Comment comment = commentsApi.createComment(post.getId(), createTestComment());
        Comment savedComment = currentUserCommentsApi
                .getCommentByCurrentUser(comment.getId());
        Comment updateComment = new Comment()
                .text("newText");
        Comment updated = currentUserCommentsApi.updateCommentByCurrentUser(savedComment.getId(), updateComment);
        assertComment(savedComment, updateComment, updated);
    }
}

