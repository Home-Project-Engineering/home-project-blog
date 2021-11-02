package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.presentation.converters.TagConverter;
import com.homeproject.blog.backend.business.models.DTO.TagDTO;
import com.homeproject.blog.backend.business.services.TagService;
import com.homeproject.blog.backend.database.repositories.TagRepository;
import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagConverter tagConverter;

    @Override
    public TagDTO readTag(Long id) {
        TagEntity entity = verifyTagExisting(id);
        return tagConverter.entityToTag(entity);
    }

    @Override
    public Collection<TagDTO> getTags() {
        Iterable<TagEntity> entities = tagRepository.findAll();
        ArrayList<TagEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        return tagConverter.entitiesToTags(list);
    }

    @Override
    public void deleteTag(Long id) {
        verifyTagExisting(id);
        tagRepository.deleteById(id);
    }

    public List<TagEntity> identifyTags(List<TagEntity> tags) {
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


    public Page<TagDTO> findAll(Long id, String name, PageRequest pageRequest) {
        Page<TagEntity> allByIdAndName = tagRepository.findAllByIdAndName(pageRequest, id, name);
        return new PageImpl<>(allByIdAndName.stream()
                .map(tagConverter::entityToTag)
                .collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }

    private TagEntity identifyTag(TagEntity tag) {
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

    private TagEntity verifyTagExisting(Long id) {
        Optional<TagEntity> result = tagRepository.findById(id);
        return result.get();
    }

}
