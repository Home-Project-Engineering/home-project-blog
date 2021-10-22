package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.dtos.Tag;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagConverterImpl implements TagConverter {

    @Override
    public Tag entityToTag(TagEntity entity) {
        Tag tag = new Tag();
        tag.setId(entity.getId());
        tag.setName(entity.getName());
        return tag;
    }

    @Override
    public TagEntity tagToEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setId(tag.getId());
        entity.setName(tag.getName());
        return entity;
    }

    @Override
    public List<TagEntity> tagsToEntities(List<Tag> tags) {
        if (tags == null) {
            return null;
        }
        Stream<TagEntity> stream = tags.stream().map(this::tagToEntity);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Tag> entitiesToTags(List<TagEntity> entities) {
        if (entities == null) {
            return null;
        }
        Stream<Tag> stream = entities.stream().map(this::entityToTag);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
