package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.businesslayer.converters.TagConverter;
import com.homeproject.blog.backend.data.repository.TagRepository;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void deleteTag(Long id) throws TagNotFoundException {
        verifyTagExisting(id);
        tagRepository.deleteById(id);
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
    public Page<Tag> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Sort sorting;
        if (sort != null) {
            if (sort.charAt(0) == '-') {
                sorting = Sort.by(sort.substring(1)).descending();
            } else {
                sorting = Sort.by(sort);
            }
        } else {
            sorting = Sort.by("name");
        }
        pageNum = pageNum == null ? pageNum = 0 : pageNum;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sorting);
        Page<TagEntity> allByIdAndName = tagRepository.findAllByIdAndName(pageRequest, id, name);
        Page<Tag> page = new PageImpl<>(allByIdAndName.stream().map(tagConverter::entityToTag).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
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