package com.homeproject.blog.backend.presentation.converters;

import com.homeproject.blog.backend.business.models.DTO.TagDTO;
import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagConverter {
    public TagDTO entityToTag(TagEntity entity) {
        TagDTO tag = new TagDTO();
        tag.setId(entity.getId());
        tag.setTag(entity.getName());
        return tag;
    }

    public TagEntity tagToEntity(TagDTO tag) {
        TagEntity entity = new TagEntity();
        entity.setId(tag.getId());
        entity.setName(tag.getTag());
        return entity;
    }

    public List<TagEntity> tagsToEntities(List<TagDTO> tags) {
        if (tags == null) {
            return null;
        }
        Stream<TagEntity> stream = tags.stream().map(this::tagToEntity);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public List<TagDTO> entitiesToTags(List<TagEntity> entities) {
        if (entities == null) {
            return null;
        }
        Stream<TagDTO> stream = entities.stream().map(this::entityToTag);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
