package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.business.convertersBetweenServiceAndController.TagConverter;
import com.homeproject.blog.backend.business.models.DTO.Tag;
import com.homeproject.blog.backend.database.repositories.TagRepository;
import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagServiceImpl {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagConverter tagConverter;

    public Tag readTag(Long id){
        TagEntity entity = verifyTagExisting(id);
        return tagConverter.entityToTag(entity);
    }

    public Collection<Tag> getTags() {
        Iterable<TagEntity> entities = tagRepository.findAll();
        ArrayList<TagEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        return tagConverter.entitiesToTags(list);
    }

    public void deleteTag(Long id) {
        verifyTagExisting(id);
        tagRepository.deleteById(id);
    }

    public List<TagEntity> identifyTags(List<Tag> tags) {
        if (tags == null) {
            return null;
        }
        Stream<TagEntity> stream = tags.stream().map(tag -> {
            TagEntity entity = identifyTag(tag);
            tag.setId(entity.getId());
            return entity;
        });
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public Page<Tag> findAll(Long id, String name, PageRequest pageRequest) {
        Page<TagEntity> allByIdAndName = tagRepository.findAllByIdAndName(pageRequest, id, name);
        Page<Tag> page = new PageImpl<Tag>(allByIdAndName.stream().map(tagConverter::entityToTag).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
        return page;
    }

    private TagEntity identifyTag(Tag tag) {
        Iterable<TagEntity> entities = tagRepository.findAll();
        for (TagEntity entity : entities) {
            if (entity.getName().equals(tag.getTag())) {
                return entity;
            }
        }
        TagEntity newEntity = new TagEntity();
        newEntity.setName(tag.getTag());
        return tagRepository.save(newEntity);
    }

    private TagEntity verifyTagExisting(Long id) {
        Optional<TagEntity> result = tagRepository.findById(id);
        return result.get();
    }

}
