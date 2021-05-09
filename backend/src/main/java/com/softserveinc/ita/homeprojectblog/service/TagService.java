package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;

import java.math.BigDecimal;

public interface TagService {

    TagDto getTag(BigDecimal id);

}
