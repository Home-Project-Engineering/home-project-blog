package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.data.entity.converters.TagConverter;
import com.homeproject.blog.backend.data.repository.TagRepository;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.ForbiddenActionException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagConverter tagConverter;

    private TagEntity verifyTagExisting(Long id) throws TagNotFoundException {
        Optional<TagEntity> result = tagRepository.findById(id);
        if (result.isEmpty()) {
            throw new TagNotFoundException();
        }
        return result.get();
    }

    @Override
    public Tag readTag(Long id) throws TagNotFoundException {
        TagEntity entity = verifyTagExisting(id);
        return tagConverter.entityToTag(entity);
    }

    @Override
    public Collection<Tag> getTags() {
        Iterable<TagEntity> entities = tagRepository.findAll();
        ArrayList<TagEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        return tagConverter.entitiesToTags(list);
    }

    @Override
    public void deleteTag(Long id) throws TagNotFoundException, ForbiddenActionException {
        verifyTagExisting(id);
        try {
            tagRepository.deleteById(id);
        } catch (Exception exception) {
            throw new ForbiddenActionException();
        }
    }

    @Override
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

    @Override
    public Page<Tag> findAll(Long id, String name, PageRequest pageRequest) {
        Page<TagEntity> allByIdAndName = tagRepository.findAllByIdAndName(pageRequest, id, name);
        Page<Tag> page = new PageImpl<Tag>(allByIdAndName.stream().map(tagConverter::entityToTag).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
        return page;
    }

    private TagEntity identifyTag(Tag tag) {
        Iterable<TagEntity> entities = tagRepository.findAll();
        for (TagEntity entity : entities) {
            if (entity.getName().equals(tag.getName())) {
                return entity;
            }
        }
        TagEntity newEntity = new TagEntity();
        newEntity.setName(tag.getName());
        return tagRepository.save(newEntity);
    }
}