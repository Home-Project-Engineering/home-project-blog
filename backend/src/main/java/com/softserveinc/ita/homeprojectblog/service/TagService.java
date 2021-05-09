package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface TagService {

    TagDto getTag(BigDecimal id);

    Page<TagDto> getTags(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize);
}
