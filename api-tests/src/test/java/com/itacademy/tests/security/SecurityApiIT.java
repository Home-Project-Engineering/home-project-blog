package com.itacademy.tests.security;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiClient;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.ApiResponse;
import com.softserveinc.ita.homeproject.blog.client.api.*;
import com.softserveinc.ita.homeproject.blog.client.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class SecurityApiIT {
    @Parameterized.Parameter
    public String actionSummary;
    @Parameterized.Parameter(1)
    public Function<ApiClient, ApiResponse<?>> action;
    @Parameterized.Parameter(2)
    public boolean admin;
    @Parameterized.Parameter(3)
    public boolean moderator;
    @Parameterized.Parameter(4)
    public boolean blogger;
    @Parameterized.Parameter(5)
    public boolean any;

    @Parameterized.Parameters(name = "{index}-{0}")
    public static Iterable<?> data() {
        Set<Object> data = new HashSet<>();

        Function<ApiClient, ApiResponse<?>> action = (ApiClient apiClient) -> {
            CurrentUserApi currentUserApi = new CurrentUserApi(apiClient);
            return currentUserApi.getCurrentUserWithHttpInfo();
        };
        data.add(Arrays.asList(
                "Get current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );
        action = (ApiClient apiClient) -> {
            CurrentUserApi currentUserApi = new CurrentUserApi(apiClient);
            return currentUserApi.getCurrentUserWithHttpInfo();
        };
        data.add(Arrays.asList(
                "Update current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        //Comment of Cur USER
        action = (ApiClient apiClient) -> {
            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
            return currentUserCommentsApi.getCommentsByCurrentUserWithHttpInfo(new BigDecimal(-1), null, null, null);
        };
        data.add(Arrays.asList(
                "Get comments current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );
        action = (ApiClient apiClient) -> {
            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
            return currentUserCommentsApi.getCommentByCurrentUserWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Get specific comment current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );
        action = (ApiClient apiClient) -> {
            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
            return currentUserCommentsApi.updateCommentByCurrentUserWithHttpInfo(new BigDecimal(-1), new Comment());
        };
        data.add(Arrays.asList(
                "Update comment current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );
        action = (ApiClient apiClient) -> {
            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
            return currentUserCommentsApi.removeCommentByCurrentUserWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Delete specific comment current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        //Posts of Cur User

        action = (ApiClient apiClient) -> {
            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
            return currentUserPostsApiApi.getPostsByCurrentUserWithHttpInfo(null, null, null, null, null, null);
        };
        data.add(Arrays.asList(
                "Get posts current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
            return currentUserPostsApiApi.getPostsByCurrentUserWithHttpInfo(null, null, null, null, null, null);
        };
        data.add(Arrays.asList(
                "Get specific post current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
            return currentUserPostsApiApi.updatePostByCurrentUserWithHttpInfo(new BigDecimal(-1), new Post());
        };
        data.add(Arrays.asList(
                "Update post current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
            return currentUserPostsApiApi.removePostByCurrentUserWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Delete post current user",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        //User management

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.createUserWithHttpInfo(new User());
        };
        data.add(Arrays.asList(
                "Create new User",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.getUsersWithHttpInfo(null, null, null, null, null);
        };
        data.add(Arrays.asList(
                "See all Users",
                action,
                true,
                false,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.getUserWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "See specific User",
                action,
                true,
                false,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.updateUserWithHttpInfo(new BigDecimal(-1), new User()
                    .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                    .firstName("firstName")
                    .lastName("lastName")
                    .password("passworD321")
                    .email(RandomStringUtils.randomAlphabetic(5).concat("@example.com")));
        };
        data.add(Arrays.asList(
                "Update User",
                action,
                true,
                false,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.removeUserWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Delete User",
                action,
                true,
                false,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.getUserRoleWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Get User Role",
                action,
                true,
                false,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            UsersApi userApi = new UsersApi(apiClient);
            return userApi.updateUserRoleWithHttpInfo(new BigDecimal(-1), new Role());
        };
        data.add(Arrays.asList(
                "Update User Role",
                action,
                true,
                false,
                false,
                false).toArray()
        );
        //Posts

        action = (ApiClient apiClient) -> {
            PostsApi postsApi = new PostsApi(apiClient);
            return postsApi.createPostWithHttpInfo(new Post().title("t"));
        };
        data.add(Arrays.asList(
                "Create post",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            PostsApi postsApi = new PostsApi(apiClient);
            return postsApi.getPostsWithHttpInfo(null, null, null, null, null, null, null);
        };
        data.add(Arrays.asList(
                "Get all posts",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            PostsApi postsApi = new PostsApi(apiClient);
            return postsApi.getPostWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Get specific post",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            PostsApi postsApi = new PostsApi(apiClient);
            return postsApi.updatePostWithHttpInfo(new BigDecimal(-1), new Post().
                    title(RandomStringUtils.randomAlphabetic(5)).
                    text(RandomStringUtils.randomAlphabetic(5)).
                    previewAttachment(RandomStringUtils.randomAlphabetic(5)).
                    tags(Arrays.asList(new Tag().name(RandomStringUtils.randomAlphabetic(5))
                            , new Tag().name(RandomStringUtils.randomAlphabetic(5)))));
        };
        data.add(Arrays.asList(
                "Update post",
                action,
                true,
                true,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            PostsApi postsApi = new PostsApi(apiClient);
            return postsApi.removePostWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Delete post",
                action,
                true,
                true,
                false,
                false).toArray()
        );

        //Comments

        action = (ApiClient apiClient) -> {
            CommentsApi commentsApi = new CommentsApi(apiClient);
            return commentsApi.createCommentWithHttpInfo(new BigDecimal(-1), new Comment());
        };
        data.add(Arrays.asList(
                "Create comment",
                action,
                true,
                true,
                true,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            CommentsApi commentsApi = new CommentsApi(apiClient);
            return commentsApi.getCommentsWithHttpInfo(null, null, null, null, null, null);
        };
        data.add(Arrays.asList(
                "Get all comments",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            CommentsApi commentsApi = new CommentsApi(apiClient);
            return commentsApi.getCommentWithHttpInfo(new BigDecimal(-1), new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Get concrete comment",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            CommentsApi commentsApi = new CommentsApi(apiClient);
            return commentsApi.updateCommentWithHttpInfo(new BigDecimal(-1), new BigDecimal(-1),
                    new Comment().text(RandomStringUtils.randomAlphabetic(5)));
        };
        data.add(Arrays.asList(
                "Update comment",
                action,
                true,
                true,
                false,
                false).toArray()
        );

        action = (ApiClient apiClient) -> {
            CommentsApi commentsApi = new CommentsApi(apiClient);
            return commentsApi.removeCommentWithHttpInfo(new BigDecimal(-1), new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Delete comment",
                action,
                true,
                true,
                false,
                false).toArray()
        );


        //Tags

        action = (ApiClient apiClient) -> {
            TagsApi tagApi = new TagsApi(apiClient);
            return tagApi.getTagsWithHttpInfo(null, null, null, null, null);
        };
        data.add(Arrays.asList(
                "Get all tags",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            TagsApi tagApi = new TagsApi(apiClient);
            return tagApi.getTagWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Get specific tag",
                action,
                true,
                true,
                true,
                true).toArray()
        );

        action = (ApiClient apiClient) -> {
            TagsApi tagApi = new TagsApi(apiClient);
            return tagApi.removeTagWithHttpInfo(new BigDecimal(-1));
        };
        data.add(Arrays.asList(
                "Delete tag",
                action,
                true,
                true,
                false,
                false).toArray()
        );

        return data;
    }

    @Test
    public void testAdmin() {
        ApiClient adminClient = ApiClientUtil.getAdminClient();
        int statusCode = getStatusCode(adminClient);
        ApiClientUtil.checkAdminModerBlogger(admin, statusCode);
    }

    @Test
    public void testModerator() {
        ApiClient moderatorClient = ApiClientUtil.getModeratorClient();
        int statusCode = getStatusCode(moderatorClient);
        ApiClientUtil.checkAdminModerBlogger(moderator, statusCode);
    }

    @Test
    public void testBlogger() {
        ApiClient bloggerClient = ApiClientUtil.getBloggerClient();
        int statusCode = getStatusCode(bloggerClient);
        ApiClientUtil.checkAdminModerBlogger(blogger, statusCode);
    }

    @Test
    public void testUnauthorizedClient() {
        ApiClient unauthorizedClient = ApiClientUtil.getUnauthorizedClient();
        int statusCode = getStatusCode(unauthorizedClient);

        if (any) {
            assertNotEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
        } else {
            assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), statusCode);
        }
    }

    private int getStatusCode(ApiClient unauthorizedClient) {
        int statusCode;
        try {
            ApiResponse<?> resp = action.apply(unauthorizedClient);
            statusCode = resp.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
        return statusCode;
    }

}
