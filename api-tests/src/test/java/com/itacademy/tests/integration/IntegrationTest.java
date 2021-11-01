package com.itacademy.tests.integration;

import com.itacademy.tests.GeneralApi;
import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.client.api.CommentsApi;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.api.TagsApi;
import com.softserveinc.ita.homeproject.blog.client.api.UsersApi;
import com.softserveinc.ita.homeproject.blog.client.model.Comment;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import com.softserveinc.ita.homeproject.blog.client.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class IntegrationTest implements GeneralApi {
    private final UsersApi userApi = new UsersApi(ApiClientUtil.getAdminClient());
    private final TagsApi tagsApi = new TagsApi(ApiClientUtil.getAdminClient());
    private final PostsApi postsApi = new PostsApi(ApiClientUtil.getAdminClient());
    private final CommentsApi commentsApi = new CommentsApi(ApiClientUtil.getAdminClient());

    @Test
    void removeUserWhoCreatedPost() {
        User user = new UsersApi().createUser(createTestUser());
        PostsApi myPostsApi = new PostsApi(ApiClientUtil.getClient(user.getEmail(), DEFAULT_PASSWORD));
        Post post = myPostsApi.createPost(createTestPost());
        assertPost(post,postsApi.getPost(post.getId()));
        userApi.removeUser(user.getId());
        assertPost(post,postsApi.getPost(post.getId()));
        postsApi.removePost(post.getId());
    }

    @Test
    void removeUserWhoCreatedComment() {
        User user = new UsersApi().createUser(createTestUser());
        Post post = postsApi.createPost(createTestPost());
        CommentsApi myCommentsApi = new CommentsApi(ApiClientUtil.getClient(user.getEmail(), DEFAULT_PASSWORD));
        Comment comment = myCommentsApi.createComment(post.getId(),createTestComment());
        assertComment(comment,commentsApi.getComment(post.getId(), comment.getId()));
        userApi.removeUser(user.getId());
        assertComment(comment,commentsApi.getComment(post.getId(), comment.getId()));
    }

    @Test
    void removeAllTagsInPost() {
        Post post = postsApi.createPost(createTestPost());
        List<Tag> tags = Objects.requireNonNull(post.getTags());
        for (int i = 0; i < tags.size(); i++) {
            tagsApi.removeTag(tags.get(0).getId());
            tags.remove(0);
            assertTags(tags, postsApi.getPost(post.getId()).getTags());
        }
    }
}
