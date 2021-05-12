package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.TagRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.TagMapperService;
import com.softserveinc.ita.home.home_project_blog.specification.SpecificationService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;

@Validated
@RequiredArgsConstructor
@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;
    private final TagMapperService mapper;
    private final SpecificationService<Tag> tagSpecificationService;

    @Override
    public Page<TagDto> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Map<String, String> filter = new HashMap<>();
        filter.put("id", id!=null?id.toString():null);
        filter.put("name", name);
        Specification<Tag> specification = tagSpecificationService.getSpecification(filter);
        Page<Tag> pageTag = tagRepository.findAll(specification, paging);
        return mapper.toPageTagDto(pageTag);
    }

    @Override
    public TagDto getById(Long id) {
        return mapper.toTagDto(tagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.TAG_DOESNT_EXIST)));
    }

    @Override
    public void delete(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException(Const.TAG_DOESNT_EXIST);
        }
        //TODO posts_tags verify
        tagRepository.deleteById(id);
    }
}
