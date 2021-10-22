package com.homeproject.blog.backend.business.services;

import com.homeproject.blog.backend.business.models.DTO.Tag;
import com.homeproject.blog.backend.persistence.entity.TagEntity;

import java.util.Collection;
import java.util.List;

public interface TagService {
    List<Tag> createTags(List<Tag> tags);

    Tag updateTag(Long postId);

    Tag readTag(Long id);

    Collection<Tag> getTags();

    void deleteTag(Long id);

    List<TagEntity> identifyTags(List<TagEntity> tags);
}
