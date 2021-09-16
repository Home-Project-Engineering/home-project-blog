package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;
import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TagMapperService {

    TagDto toTagDto(TagEntity tagEntity);

    default Page<TagDto> toTagDtoPage(Page<TagEntity> tagEntityPage) {
        return tagEntityPage.map(this::toTagDto);
    }

}
