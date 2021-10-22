package com.homeproject.blog.backend.business.convertersBetweenServiceAndController;

import com.homeproject.blog.backend.business.models.DTO.Tag;
import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagConverter {
    public Tag entityToTag(TagEntity entity) {
        Tag tag = new Tag();
        tag.setId(entity.getId());
        tag.setTag(entity.getName());
        return tag;
    }

    public TagEntity tagToEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setId(tag.getId());
        entity.setName(tag.getTag());
        return entity;
    }

    public List<TagEntity> tagsToEntities(List<Tag> tags) {
        if (tags == null) {
            return null;
        }
        Stream<TagEntity> stream = tags.stream().map(this::tagToEntity);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Tag> entitiesToTags(List<TagEntity> entities) {
        if (entities == null) {
            return null;
        }
        Stream<Tag> stream = entities.stream().map(this::entityToTag);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
