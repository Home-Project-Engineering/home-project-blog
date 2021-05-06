package com.itacademy.tests.tags;

import com.itacademy.tests.utils.ApiClientUtil;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.client.api.PostsApi;
import com.softserveinc.ita.homeproject.blog.client.api.TagsApi;
import com.softserveinc.ita.homeproject.blog.client.model.Post;
import com.softserveinc.ita.homeproject.blog.client.model.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class TagApiIT {
    TagsApi tagsApi = new TagsApi(ApiClientUtil.getClient());
    PostsApi postsApi = new PostsApi(ApiClientUtil.getClient());

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
                ,"-id"
                ,1
                ,10
        );
        assertThat(tags).isNotEmpty();
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
    private Tag createTestTag(){
        return new Tag().name(RandomStringUtils.randomAlphabetic(5));
    }

    private void assertTag(Tag expected, Tag actual) {
        assertNotNull(expected);
        assertEquals(expected.getName(), actual.getName());
    }

}
