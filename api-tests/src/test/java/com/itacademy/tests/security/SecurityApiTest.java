package com.itacademy.tests.security;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiClient;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.ApiResponse;
import com.softserveinc.ita.homeproject.blog.client.api.*;
import com.softserveinc.ita.homeproject.blog.client.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityApiTest {


    @ParameterizedTest(name = "{index}-{1}")
    @MethodSource("check")
    void testAdmin(Function<ApiClient, ApiResponse<?>> action, String x, boolean a) {

        int statusCode = getStatusCode(action, ApiClientUtil.getAdminClient());
        checkAdminModerBlogger(a, statusCode);
    }

    @ParameterizedTest(name = "{index}-{1}")
    @MethodSource("check")
    void testModerator(Function<ApiClient, ApiResponse<?>> action, String x, boolean a, boolean m) {

        int statusCode = getStatusCode(action, ApiClientUtil.getClient("v_moderator@example.com", "Dfkthrf17"));
        checkAdminModerBlogger(m, statusCode);

    }

    @ParameterizedTest(name = "{index}-{1}")
    @MethodSource("check")
     void testBlogger(Function<ApiClient, ApiResponse<?>> action, String x, boolean a, boolean m, boolean b) {
        int statusCode = getStatusCode(action, ApiClientUtil.getClient("v_blogger@example.com", "Dfkthrf17"));
        checkAdminModerBlogger(b, statusCode);
    }

    @ParameterizedTest(name = "{index}-{1}")
    @MethodSource("check")
     void testUnauthorizedClient(Function<ApiClient, ApiResponse<?>> action, boolean a, boolean m, boolean b, boolean un) {

        int statusCode = getStatusCode(action, ApiClientUtil.getUnauthorizedClient());
        if (un) {
            Assertions.assertNotEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
        } else {
            Assertions.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), statusCode);
        }

    }


    private static Stream<Arguments> check() {
        return Stream.of(Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                    CurrentUserApi currentUserApi = new CurrentUserApi(apiClient);
                    return currentUserApi.getCurrentUserWithHttpInfo();
                },
                "Get current User",
                true,
                true,
                true,
                false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserApi currentUserApi = new CurrentUserApi(apiClient);
                            return currentUserApi.getCurrentUserWithHttpInfo();
                        },
                        "Update current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
                            return currentUserCommentsApi.getCommentsByCurrentUserWithHttpInfo(new BigDecimal(-1), null, null, null);
                        },
                        "Get comments current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
                            return currentUserCommentsApi.getCommentByCurrentUserWithHttpInfo(new BigDecimal(-1));
                        },
                        "Get specific comment current User",
                        true,
                        true,
                        true,
                        false),
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
                            return currentUserCommentsApi.updateCommentByCurrentUserWithHttpInfo(new BigDecimal(-1), new Comment());
                        },
                        "Update comment current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserCommentsApi currentUserCommentsApi = new CurrentUserCommentsApi(apiClient);
                            return currentUserCommentsApi.removeCommentByCurrentUserWithHttpInfo(new BigDecimal(-1));
                        },
                        "Delete specific comment current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
                            return currentUserPostsApiApi.getPostsByCurrentUserWithHttpInfo(null, null, null, null, null, null);
                        },
                        "Get posts current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
                            return currentUserPostsApiApi.getPostsByCurrentUserWithHttpInfo(null, null, null, null, null, null);
                        },
                        "Get specific post current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
                            return currentUserPostsApiApi.updatePostByCurrentUserWithHttpInfo(new BigDecimal(-1), new Post());
                        },
                        "Update post current User",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CurrentUserPostsApi currentUserPostsApiApi = new CurrentUserPostsApi(apiClient);
                            return currentUserPostsApiApi.removePostByCurrentUserWithHttpInfo(new BigDecimal(-1));
                        },
                        "Delete post current User",
                        true,
                        true,
                        true,
                        false),
                ////
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.createUserWithHttpInfo(new User());
                        },
                        "Create new User",
                        true,
                        true,
                        true,
                        true),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.getUsersWithHttpInfo(null, null, null, null, null);
                        },
                        "See all User",
                        true,
                        false,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.getUserWithHttpInfo(new BigDecimal(-1));
                        },
                        "See specific User",
                        true,
                        false,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.updateUserWithHttpInfo(new BigDecimal(-1), new User()
                                    .name(RandomStringUtils.randomAlphabetic(5).concat("_test"))
                                    .firstName("firstName")
                                    .lastName("lastName")
                                    .password("passworD321")
                                    .email(RandomStringUtils.randomAlphabetic(5).concat("@example.com")));
                        },
                        "Update User",
                        true,
                        false,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.removeUserWithHttpInfo(new BigDecimal(-1));
                        },
                        "Delete User",
                        true,
                        false,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.getUserRoleWithHttpInfo(new BigDecimal(-1));
                        },
                        "Get User Role",
                        true,
                        false,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            UsersApi userApi = new UsersApi(apiClient);
                            return userApi.updateUserRoleWithHttpInfo(new BigDecimal(-1), new Role());
                        },
                        "Update User Role",
                        true,
                        false,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            PostsApi postsApi = new PostsApi(apiClient);
                            return postsApi.createPostWithHttpInfo(new Post().title("t"));
                        },
                        "Create post",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            PostsApi postsApi = new PostsApi(apiClient);
                            return postsApi.getPostsWithHttpInfo(null, null, null, null, null, null, null);
                        },
                        "Get all posts",
                        true,
                        true,
                        true,
                        true),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            PostsApi postsApi = new PostsApi(apiClient);
                            return postsApi.getPostWithHttpInfo(new BigDecimal(-1));
                        },
                        "Get specific post",
                        true,
                        true,
                        true,
                        true),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            PostsApi postsApi = new PostsApi(apiClient);
                            return postsApi.updatePostWithHttpInfo(new BigDecimal(-1), new Post().
                                    title(RandomStringUtils.randomAlphabetic(5)).
                                    text(RandomStringUtils.randomAlphabetic(5)).
                                    previewAttachment(RandomStringUtils.randomAlphabetic(5)).
                                    tags(Arrays.asList(new Tag().name(RandomStringUtils.randomAlphabetic(5))
                                            , new Tag().name(RandomStringUtils.randomAlphabetic(5)))));
                        },
                        "Update post",
                        true,
                        true,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            PostsApi postsApi = new PostsApi(apiClient);
                            return postsApi.removePostWithHttpInfo(new BigDecimal(-1));
                        },
                        "Delete post",
                        true,
                        true,
                        false,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CommentsApi commentsApi = new CommentsApi(apiClient);
                            return commentsApi.createCommentWithHttpInfo(new BigDecimal(-1), new Comment());
                        },
                        "Create comment",
                        true,
                        true,
                        true,
                        false),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CommentsApi commentsApi = new CommentsApi(apiClient);
                            return commentsApi.getCommentsWithHttpInfo(null, null, null, null, null, null);
                        },
                        "Get all comments",
                        true,
                        true,
                        true,
                        true),

                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CommentsApi commentsApi = new CommentsApi(apiClient);
                            return commentsApi.getCommentWithHttpInfo(new BigDecimal(-1), new BigDecimal(-1));
                        },
                        "Get concrete comment",
                        true,
                        true,
                        true,
                        true),
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CommentsApi commentsApi = new CommentsApi(apiClient);
                            return commentsApi.updateCommentWithHttpInfo(new BigDecimal(-1), new BigDecimal(-1),
                                    new Comment().text(RandomStringUtils.randomAlphabetic(5)));
                        },
                        "Update comment",
                        true,
                        true,
                        false,
                        false),
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            CommentsApi commentsApi = new CommentsApi(apiClient);
                            return commentsApi.removeCommentWithHttpInfo(new BigDecimal(-1), new BigDecimal(-1));
                        },
                        "Delete comment",
                        true,
                        true,
                        false,
                        false),
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            TagsApi tagApi = new TagsApi(apiClient);
                            return tagApi.getTagsWithHttpInfo(null, null, null, null, null);
                        },
                        "Get all tags",
                        true,
                        true,
                        true,
                        true),
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            TagsApi tagApi = new TagsApi(apiClient);
                            return tagApi.getTagWithHttpInfo(new BigDecimal(-1));
                        },
                        "Get specific tag",
                        true,
                        true,
                        true,
                        true),
                Arguments.of((Function<ApiClient, ApiResponse<?>>) (ApiClient apiClient) -> {
                            TagsApi tagApi = new TagsApi(apiClient);
                            return tagApi.removeTagWithHttpInfo(new BigDecimal(-1));
                        },
                        "Delete tag",
                        true,
                        true,
                        false,
                        false)

        );
    }

    private int getStatusCode(Function<ApiClient, ApiResponse<?>> action, ApiClient unauthorizedClient) {
        int statusCode;
        try {
            ApiResponse<?> resp = action.apply(unauthorizedClient);
            statusCode = resp.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
        return statusCode;
    }

    public void checkAdminModerBlogger(boolean role, int statusCode) {
        if (role) {
            Assertions.assertNotEquals(Response.Status.UNAUTHORIZED.getStatusCode(), statusCode);
            Assertions.assertNotEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
        } else {
            assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
        }
    }
}
