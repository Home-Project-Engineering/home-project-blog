package com.itacademy.tests.tags;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.api.TagsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class TagApiIT {
    TagsApi tagsApi = new TagsApi(ApiClientUtil.getAdminClient());
    PostsApi postsApi = new PostsApi(ApiClientUtil.getAdminClient());

    @Test
    void removeTag() {
        Tag tag = Objects.requireNonNull(postsApi.createPost(createTestPostWithTag()).getTags()).get(0);
        tagsApi.removeTag(tag.getId());
        assertThatExceptionOfType(ApiException.class)
                .isThrownBy(() -> tagsApi.getTag(tag.getId()));
    }

    @Test
    void getTags() {
        postsApi.createPost(createTestPostWithTags());
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
        assertThat(tags).isSortedAccordingTo(Comparator.comparing(Tag::getName));
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
        assertThat(tags).isSortedAccordingTo(Comparator.comparing(Tag::getName).reversed());
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
        Tag tag = Objects.requireNonNull(postsApi.createPost(createTestPostWithTag()).getTags()).get(0);
        Tag actual = tagsApi.getTag(tag.getId());
        assertTag(tag, actual);
    }


    private Post createTestPostWithTag() {
        return new Post().
                title(RandomStringUtils.randomAlphabetic(5)).
                previewAttachment(RandomStringUtils.randomAlphabetic(5)).
                text(RandomStringUtils.randomAlphabetic(5)).
                tags(Collections.singletonList(createTestTag()));
    }

    private Post createTestPostWithTags() {
        return new Post().
                title(RandomStringUtils.randomAlphabetic(5)).
                text(RandomStringUtils.randomAlphabetic(5)).
                previewAttachment(RandomStringUtils.randomAlphabetic(5)).
                tags(Arrays.asList(createTestTag(), createTestTag(), createTestTag(), createTestTag()));
    }

    private Tag createTestTag() {
        return new Tag().name(RandomStringUtils.randomAlphabetic(5));
    }

    private void assertTag(Tag expected, Tag actual) {
        assertNotNull(expected);
        assertEquals(expected.getName(), actual.getName());
    }

}
