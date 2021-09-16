package com.itacademy.tests.curentUser;

import com.itacademy.tests.GeneralApi;
import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.CurrentUserPostsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class CurrentUserPostsApiIT implements GeneralApi {
    private final PostsApi postsApi = new PostsApi(ApiClientUtil.getAdminClient());
    private final CurrentUserPostsApi currentUserPostsApi = new CurrentUserPostsApi(ApiClientUtil.getAdminClient());

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
        saveListPosts(postsApi);
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
}
