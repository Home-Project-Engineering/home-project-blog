package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Tag;
import com.homeproject.blog.backend.data.repositories.PostRepository;
import com.homeproject.blog.backend.data.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    ConversionService conversionService;

    @Override
    public TagDTO getTag(Long id) {
        Tag tag = tagRepository.getById(id);
        return conversionService.convert(tag, TagDTO.class) ;
    }

    @Override
    public Page<TagDTO> getTags(Long id, String name, PageRequest pageRequest) {
        Page<Tag> allByIdAndName = tagRepository.findAllByIdAndName(pageRequest, id, name);
        return new PageImpl<>(allByIdAndName.stream().map(tag -> conversionService.convert(tag, TagDTO.class)).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }


    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteRelation(id);
        tagRepository.deleteById(id);
    }
}
