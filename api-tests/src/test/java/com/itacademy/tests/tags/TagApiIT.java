package com.itacademy.tests.tags;

import com.itacademy.tests.GeneralApi;
import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.api.TagsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class TagApiIT implements GeneralApi {
    TagsApi tagsApi = new TagsApi(ApiClientUtil.getAdminClient());
    PostsApi postsApi = new PostsApi(ApiClientUtil.getAdminClient());

    @Test
    void removeTag() {
        Post post = postsApi.createPost(createTestPost(1));
        Tag tag = Objects.requireNonNull(post.getTags()).get(0);
        tagsApi.removeTag(tag.getId());
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> tagsApi.getTag(tag.getId()));
    }

    @Test
    void getTags() {
        postsApi.createPost(createTestPost());
        List<Tag> tags = tagsApi.getTags(
                null
                , null
                , "-id"
                , 1
                , 10
        );
        assertThat(tags).isNotEmpty();
    }

    @Test
    void tagsShouldBeSortedByAscName() {
        List<Tag> tags = tagsApi.getTags(
                null
                , null
                , "name"
                , 1
                , 10
        );
        assertThat(tags).isSortedAccordingTo(Comparator.comparing((Tag tag) -> tag.getName().toLowerCase()));
    }

    @Test
    void tagsShouldBeSortedByDescName() {
        List<Tag> tags = tagsApi.getTags(
                null
                , null
                , "-name"
                , 1
                , 10
        );
        assertThat(tags).isSortedAccordingTo(Comparator.comparing((Tag tag) -> tag.getName().toLowerCase()).reversed());
    }

    @Test
    void tagsShouldBeSortedByAscId() {
        List<Tag> tags = tagsApi.getTags(
                null
                , null
                , "id"
                , 1
                , 10
        );
        assertThat(tags).isSortedAccordingTo(Comparator.comparing(Tag::getId));
    }

    @Test
    void tagsShouldBeSortedByDescId() {
        List<Tag> tags = tagsApi.getTags(
                null
                , null
                , "-id"
                , 1
                , 10
        );
        assertThat(tags).isSortedAccordingTo(Comparator.comparing(Tag::getId).reversed());
    }

    @Test
    void getTag() {
        Tag tag = Objects.requireNonNull(postsApi.createPost(createTestPost()).getTags()).get(0);
        Tag actual = tagsApi.getTag(tag.getId());
        assertTag(tag, actual);
    }

    private void assertTag(Tag expected, Tag actual) {
        assertNotNull(expected);
        assertEquals(expected.getName(), actual.getName());
    }

}
