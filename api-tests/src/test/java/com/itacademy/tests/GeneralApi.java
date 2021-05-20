package com.itacademy.tests;

import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.CommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Comment;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import com.softserveinc.ita.homeproject.blog.client.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public interface GeneralApi {
    String DEFAULT_PASSWORD = "passworD321";

    default User createTestUser() {
        return new User()
                .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                .firstName("firstName")
                .lastName("lastName")
                .password(DEFAULT_PASSWORD)
                .email(RandomStringUtils.randomAlphabetic(5).concat("@example.com"));
    }

    //*****************POST*******************

    default List<Post> createPostList() {
        List<Post> list = new ArrayList<>();
        list.add(createTestPost());
        list.add(createTestPost());
        list.add(createTestPost());
        list.add(createTestPost());
        return list;
    }

    default Post createTestPost(int countOfTags) {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < countOfTags; i++) {
            tags.add(createTestTag());
        }
        return new Post().
                title(RandomStringUtils.randomAlphabetic(5)).
                text(RandomStringUtils.randomAlphabetic(5)).
                previewAttachment(RandomStringUtils.randomAlphabetic(5)).
                tags(tags);
    }

    default Post createTestPost() {
        return createTestPost(5);
    }

    default void assertPost(Post saved, Post update, Post updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertPostBaseInfo(update, updated);
    }

    default void assertPostBaseInfo(Post expected, Post actual) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getText(), actual.getText());
        assertTags(expected.getTags(), actual.getTags());
    }

    default void assertTags(List<Tag> expectedTags, List<Tag> actualTags) {
        if (expectedTags != null) {
            assertNotNull(actualTags);
            List<String> expectedTagNames = tagsToTagNames(expectedTags);
            List<String> actualTagNames = tagsToTagNames(actualTags);
            assertEquals(expectedTagNames.size(), actualTagNames.size());
            expectedTagNames.stream().map(actualTagNames::contains).forEach(Assertions::assertTrue);
        } else
            assertTrue(actualTags.isEmpty());
    }

    private List<String> tagsToTagNames(List<Tag> tags) {
        return tags != null ? tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList()) : null;
    }

    default void assertPost(Post expected, Post actual) {
        assertPostBaseInfo(expected, actual);
        assertEquals(expected.getAuthor(), actual.getAuthor());
    }

    default Tag createTestTag() {
        return new Tag().name(RandomStringUtils.randomAlphabetic(5));
    }

    //******************COMMENTS*******************

    default List<Comment> createCommentList() {
        List<Comment> list = new ArrayList<>();
        list.add(createTestComment());
        list.add(createTestComment());
        list.add(createTestComment());
        list.add(createTestComment());
        return list;
    }

    default Comment createTestComment() {
        return new Comment().
                text(RandomStringUtils.randomAlphabetic(5));
    }

    default void assertCommentBaseInfo(Comment expected, Comment actual) {
        assertNotNull(expected);
        assertEquals(expected.getText(), actual.getText());
    }

    default void assertComment(Comment expected, Comment actual) {
        assertCommentBaseInfo(expected, actual);
        assertEquals(expected.getAuthor(), actual.getAuthor());
    }

    default void assertComment(Comment saved, Comment update, Comment updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertEquals(update.getText(), updated.getText());
    }

    //***************todo optimize for saving****************

    default List<Comment> saveListComment(BigDecimal postId, CommentsApi commentsApi) throws ApiException {

        List<Comment> list = createCommentList();
        List<Comment> commentList = new ArrayList<>();
        for (Comment cc : list) {
            commentList.add(commentsApi.createComment(postId, cc));
        }
        return commentList;
    }

    default List<Post> saveListPosts(PostsApi postsApi) throws ApiException {
        List<Post> list = createPostList();
        List<Post> postList = new ArrayList<>();
        for (Post cp : list) {
            postList.add(postsApi.createPost(cp));
        }
        return postList;
    }
}
