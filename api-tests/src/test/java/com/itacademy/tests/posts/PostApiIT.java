package com.itacademy.tests.posts;

import com.itacademy.tests.GeneralApi;
import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class PostApiIT implements GeneralApi {
    PostsApi postsApi = new PostsApi(ApiClientUtil.getAdminClient());

    @Test
    void createPost() {
        Post expectedPost = createTestPost();
        Post post = postsApi.createPost(expectedPost);
        assertPostBaseInfo(expectedPost, post);
    }

    @Test
    void createPostWithoutTags() {
        Post expectedPost = createTestPost(0);
        Post post = postsApi.createPost(expectedPost);
        assertPostBaseInfo(expectedPost, post);
    }

    @Test
    void removePost() {
        Post expectedPost = postsApi.createPost(createTestPost());
        postsApi.removePost(expectedPost.getId());

        List<Post> actualPostsList = postsApi.getPosts(
                expectedPost.getId()
                , null
                , null
                , null
                , "-id"
                , 1
                , 100);

        assertFalse(actualPostsList.contains(expectedPost));
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> postsApi.getPost(expectedPost.getId()));
    }


    @Test
    void getPosts() {
        saveListPosts(postsApi);
        List<Post> posts = postsApi.getPosts(
                null
                , null
                , null
                , null
                , "-id"
                , 1
                , 10
        );
        assertThat(posts).isNotEmpty();
    }

    @Test
    void postsShouldHaveOnlyConcretePost() {
        List<Post> saved = saveListPosts(postsApi);
        List<Post> posts = postsApi.getPosts(
                saved.get(0).getId()
                , null
                , null
                , null
                , "-id"
                , 1
                , 10
        );
        assertThat(posts).containsOnly(posts.get(0));
    }

    @Test
    void postsShouldBeSortedByDescId() {

        List<Post> posts = postsApi.getPosts(
                null
                , null
                , null
                , null
                , "-id"
                , 1
                , 10
        );

        assertThat(posts).isSortedAccordingTo(Comparator.comparing(Post::getId).reversed());
    }

    @Test
    void postsShouldBeSortedByAscId() {
        List<Post> posts = postsApi.getPosts(
                null
                , null
                , null
                , null
                , "id"
                , 1
                , 10
        );
        assertThat(posts).isSortedAccordingTo(Comparator.comparing(Post::getId));
    }

    @Test
    void getPost() {
        Post expected = postsApi.createPost(createTestPost());
        Post actual = postsApi.getPost(expected.getId());
        assertPost(expected, actual);
    }


    @Test
    void updatePost() {
        Post post = postsApi.createPost(createTestPost());
        Post updatePost = new Post()
                .previewAttachment("updatedPreviewAttachment")
                .title("updatedTitle")
                .text("updatedText")
                .tags(Arrays.asList(new Tag().name(RandomStringUtils.randomAlphabetic(5))));
        Post updated = postsApi.updatePost(post.getId(), updatePost);
        assertPost(post, updatePost, updated);
        assertNotEquals(updated.getCreatedOn(), updated.getUpdatedOn());
        assertNotEquals(post.getUpdatedOn(), updated.getUpdatedOn());
    }

    @Test
    void updatePostWithoutTags() {
        Post post = postsApi.createPost(createTestPost());
        Post updatePost = new Post()
                .previewAttachment("updatedPreviewAttachment")
                .title("updatedTitle")
                .text("updatedText");
        Post updated = postsApi.updatePost(post.getId(), updatePost);
        assertPost(post, updatePost, updated);
    }
}
