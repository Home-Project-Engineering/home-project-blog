package com.itacademy.blog.services.service.impl;

import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.data.repository.TagRepo;
import com.itacademy.blog.services.DTO.TagDTO;
import com.itacademy.blog.services.mapper.TagMapper;
import com.itacademy.blog.services.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepo tagRepo;

    @Override
    public TagDTO deleteTag(Long id) {
        Tag toDelete = tagRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag with id:" + id + " is not found"));
        tagRepo.deleteById(id);

        return TagMapper.INSTANCE.convert(toDelete);
    }

    @Override
    public TagDTO getTagById(Long id) {
        Tag toGet = tagRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id:" + id + " is not found"));
        return TagMapper.INSTANCE.convert(toGet);
    }

    @Override
    public List<TagDTO> findAllTags(Integer pageNumber, Integer pageSize, String sort, Specification<Tag> specification) {
        List<Tag> toReturn = tagRepo.findAll(specification, PageRequest
                .of(pageNumber - 1, pageSize, getSort(sort))).toList();

        return TagMapper.INSTANCE.convert(toReturn);
    }
    private Sort getSort(String sort) {
        StringBuilder str = new StringBuilder(sort);

        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());}

        return Sort.by(Sort.Direction.ASC, str.toString());
    }
}
