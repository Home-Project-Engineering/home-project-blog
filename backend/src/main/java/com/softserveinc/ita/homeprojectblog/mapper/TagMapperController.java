package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;
import com.softserveinc.ita.homeprojectblog.model.Tag;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TagMapperController {

    Tag toTag(TagDto tagDto);

    default Page<Tag> toTagPage(Page<TagDto> tagDtoPage) {
        return tagDtoPage.map(this::toTag);
    }

}
