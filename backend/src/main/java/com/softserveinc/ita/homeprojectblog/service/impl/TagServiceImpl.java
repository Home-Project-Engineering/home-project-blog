package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;
import com.softserveinc.ita.homeprojectblog.mapper.TagMapperService;
import com.softserveinc.ita.homeprojectblog.repository.TagRepository;
import com.softserveinc.ita.homeprojectblog.service.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static com.softserveinc.ita.homeprojectblog.util.Constants.TAG_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;
    TagMapperService mapper;

    @Override
    public TagDto getTag(BigDecimal id) {
        var tagEntity = tagRepository.findById(Long.valueOf(id.toString())).orElseThrow(
                () -> new EntityNotFoundException(String.format(TAG_NOT_FOUND_FORMAT, id)));
        return mapper.toTagDto(tagEntity);
    }
}
