package com.itacademy.tests.curentUser;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.CommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.CurrentUserCommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Comment;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentUserCommentsApiIT {
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
        saveListComment(post.getId());
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
    private List<Comment> saveListComment(BigDecimal postId) throws ApiException {

        List<Comment> list = createCommentList();
        List<Comment> commentList = new ArrayList<>();
        for (Comment cc : list) {
            commentList.add(commentsApi.createComment(postId, cc));
        }
        return commentList;
    }

    private List<Comment> createCommentList() {
        List<Comment> list = new ArrayList<>();
        list.add(createTestComment());
        list.add(createTestComment());
        list.add(createTestComment());
        list.add(createTestComment());
        return list;
    }
    private Post createTestPost() {
        return new Post().
                title(RandomStringUtils.randomAlphabetic(5)).
                text(RandomStringUtils.randomAlphabetic(5)).
                previewAttachment(RandomStringUtils.randomAlphabetic(5)).
                tags(Arrays.asList(new Tag().name(RandomStringUtils.randomAlphabetic(5))
                        , new Tag().name(RandomStringUtils.randomAlphabetic(5))));
    }
    private Comment createTestComment() {
        return new Comment().
                text(RandomStringUtils.randomAlphabetic(5));
    }
    private void assertComment(Comment expected, Comment actual) {
        assertNotNull(expected);
        assertEquals(expected.getText(), actual.getText());
    }
    private void assertComment(Comment saved, Comment update, Comment updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertEquals(update.getText(), updated.getText());
    }
}
