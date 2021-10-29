package com.itacademy.tests.comments;

import com.itacademy.tests.GeneralApi;
import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.CommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Comment;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class CommentApiIT implements GeneralApi {
    private final CommentsApi commentsApi = new CommentsApi(ApiClientUtil.getAdminClient());
    private final PostsApi postApi = new PostsApi(ApiClientUtil.getAdminClient());

    @Test
    void getComments() {
        Post post = postApi.createPost(createTestPost());
        saveListComment(post.getId());
        List<Comment> comments = commentsApi.getComments(
                post.getId()
                , null
                , null
                , "-id"
                , 1
                , 10
        );
        assertThat(comments).isNotEmpty();
    }

    @Test
    void getComment() {
        Post post = postApi.createPost(createTestPost());
        Comment comment = commentsApi.createComment(post.getId(), createTestComment());
        Comment actual = commentsApi.getComment(post.getId(), comment.getId());
        assertComment(comment, actual);

    }

    @Test
    void createComment() {
        Post post = postApi.createPost(createTestPost());
        Comment expected = createTestComment();
        Comment comment = commentsApi.createComment(post.getId(), expected);
        assertCommentBaseInfo(expected, comment);
    }

    @Test
    void updateComment() {
        Post post = postApi.createPost(createTestPost());
        Comment comment = commentsApi.createComment(post.getId(), createTestComment());

        Comment updateComment = new Comment()
                .text("newText");
        Comment updated = commentsApi.updateComment(post.getId(), comment.getId(), updateComment);
        assertComment(comment, updateComment, updated);
    }

    @Test
    void removeComment() {
        Post post = postApi.createPost(createTestPost());
        Comment expected = commentsApi.createComment(post.getId(), createTestComment());
        commentsApi.removeComment(post.getId(), expected.getId());

        List<Comment> actualCommentsList = commentsApi.getComments(
                post.getId()
                , expected.getId()
                , null
                , "-id"
                , 1
                , 10);

        assertFalse(actualCommentsList.contains(expected));
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> commentsApi.getComment(post.getId(), expected.getId()));
    }

    private List<Comment> saveListComment(Long postId) throws ApiException {

        List<Comment> list = createCommentList();
        List<Comment> commentList = new ArrayList<>();
        for (Comment cc : list) {
            commentList.add(commentsApi.createComment(postId, cc));
        }
        return commentList;
    }

}
