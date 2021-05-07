package com.example.blog.backend.services;


import com.example.blog.backend.repository.entities.TagEntity;
import com.example.blog.backend.repository.repos.TagRepo;
import com.example.blog.backend.util.dtos.DtoTag;
import com.example.blog.backend.util.mappers.TagMapper;
import com.example.blog.backend.util.specifications.TagSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TagService {

    private TagRepo tagRepo;
    private TagSpecification tagSec;

    public TagService(TagRepo tagRepo, TagSpecification tagSec) {
        this.tagRepo = tagRepo;
        this.tagSec = tagSec;
    }

    public DtoTag getTag(Long id) {
        if (tagRepo.findById(id).isEmpty())
            throw new EntityNotFoundException("No Tag with ID " + id);

        TagEntity entity = tagRepo.findById(id).get();
        return TagMapper.INSTANCE.toDtoFromEntity(entity);
    }

    public void delete(Long id) {
        if (tagRepo.findById(id).isEmpty())
            throw new EntityNotFoundException("No Tag with ID " + id);

        tagRepo.deleteById(id);
    }

    public Page<DtoTag> getTags(Long id, String name, String sort, Integer pageNum, Integer pageSize) {

        Specification<TagEntity> spec = tagSec.id(id).and(tagSec.name(name));

        Page<TagEntity> page;

        if (pageNum != null && pageSize != null) {
            page = tagRepo.findAll(spec, PageRequest.of(pageNum, pageSize, getSorter(sort)));
        } else {
            page = tagRepo.findAll(spec, PageRequest.of(0, 50, getSorter(sort)));
        }

        return TagMapper.INSTANCE.toPageDto(page);
    }

    private Sort getSorter(String sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "name");
        } else {
            if (sort.contains("-")) {
                String par = sort.substring(1);
                return Sort.by(Sort.Direction.DESC, par);

            } else {
                return Sort.by(Sort.Direction.ASC, sort);
            }
        }

    }
}
