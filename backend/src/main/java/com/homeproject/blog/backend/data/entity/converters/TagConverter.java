package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.dtos.Tag;

import java.util.List;

public interface TagConverter {

    Tag entityToTag(TagEntity entity);

    TagEntity tagToEntity(Tag tag);

    List<TagEntity> tagsToEntities(List<Tag> tags);

    List<Tag> entitiesToTags(List<TagEntity> entities);
}
