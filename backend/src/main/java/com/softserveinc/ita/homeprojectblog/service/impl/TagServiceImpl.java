package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;
import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import com.softserveinc.ita.homeprojectblog.mapper.TagMapperService;
import com.softserveinc.ita.homeprojectblog.repository.TagRepository;
import com.softserveinc.ita.homeprojectblog.service.TagService;
import com.softserveinc.ita.homeprojectblog.util.Checkout;
import com.softserveinc.ita.homeprojectblog.util.page.Sorter;
import com.softserveinc.ita.homeprojectblog.util.query.EntitySpecificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.softserveinc.ita.homeprojectblog.util.Constants.TAG_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;
    TagMapperService tagMapper;
    Checkout checkout;
    @Qualifier("entitySpecificationService")
    EntitySpecificationService<TagEntity> entitySpecificationService;
    Sorter sorter;

    @Override
    public TagDto getTag(BigDecimal id) {
        var tagEntity = tagRepository.findById(Long.valueOf(id.toString())).orElseThrow(
                () -> new EntityNotFoundException(String.format(TAG_NOT_FOUND_FORMAT, id)));
        return tagMapper.toTagDto(tagEntity);
    }

    @Override
    public Page<TagDto> getTags(BigDecimal id, String name,
                                String sort, Integer pageNum, Integer pageSize) {
        Map<String, String> predicateMap = new HashMap<>();
        predicateMap.put("id", id != null ? id.toString() : null);
        predicateMap.put("name", name);

        var check = checkout.checkoutAndSetDefaults(sort, pageNum, pageSize);
        var specification = entitySpecificationService.getSpecification(predicateMap);
        var pageRequest =
                PageRequest.of(check.getPageNum(),check.getPageSize(), sorter.getSorter(check.getSort()));

        var tagEntityPage = tagRepository.findAll(specification, pageRequest);

        return tagMapper.toTagDtoPage(tagEntityPage);
    }
}
