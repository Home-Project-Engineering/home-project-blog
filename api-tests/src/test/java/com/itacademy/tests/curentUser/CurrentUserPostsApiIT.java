package com.itacademy.tests.curentUser;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.CurrentUserPostsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentUserPostsApiIT {
    private final PostsApi postsApi = new PostsApi(ApiClientUtil.getClient());
    private final CurrentUserPostsApi currentUserPostsApi = new CurrentUserPostsApi(ApiClientUtil.getClient());


    @Test
    void updatePostByCurrentUser() {
        Post post = postsApi.createPost(createTestPost());
        Post savedPost = currentUserPostsApi
                .getPostByCurrentUser(post.getId());
        Post updatePost = new Post()
                .previewAttachment("newPreviewAttachment")
                .title("newTitle")
                .text("newText");
        Post updated = currentUserPostsApi.updatePostByCurrentUser(savedPost.getId(), updatePost);
        assertPost(savedPost, updatePost, updated);
    }

    @Test
    void removePostByCurrentUser() {
        Post expectedPost = postsApi.createPost(createTestPost());
        currentUserPostsApi.removePostByCurrentUser(expectedPost.getId());

        List<Post> actualPostsList = postsApi.getPosts(
                expectedPost.getId()
                ,null
                ,null
                ,null
                , "-id"
                , 1
                , 100);

        assertFalse(actualPostsList.contains(expectedPost));
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> postsApi.getPost(expectedPost.getId()));
    }
    @Test
    void getPostsByCurrentUser() {
        saveListPost();
        List<Post> posts = currentUserPostsApi.getPostsByCurrentUser(
                null
                ,null
                ,null
                ,"-id"
                ,1
                ,10
        );
        assertThat(posts).isNotEmpty();
    }
    @Test
    void getPostByCurrentUser() {
        Post expected = postsApi.createPost(createTestPost());
        Post actual = currentUserPostsApi.getPostByCurrentUser(expected.getId());
        assertPost(expected, actual);
    }



    private List<Post> saveListPost() throws ApiException {
        List<Post> list = createPostList();
        List<Post> postList = new ArrayList<>();
        for (Post cp : list) {
            postList.add(postsApi.createPost(cp));
        }
        return postList;
    }

    private List<Post> createPostList() {
        List<Post> list = new ArrayList<>();
        list.add(createTestPost());
        list.add(createTestPost());
        list.add(createTestPost());
        list.add(createTestPost());
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
    private void assertPost(Post saved, Post update, Post updated) {
        assertNotNull(updated);
        assertNotEquals(saved, updated);
        assertEquals(update.getText(), updated.getText());
        assertEquals(update.getTitle(), updated.getTitle());
    }
    private void assertPost(Post expected, Post actual) {
        assertNotNull(expected);
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getAuthor(), actual.getAuthor());
    }
}
