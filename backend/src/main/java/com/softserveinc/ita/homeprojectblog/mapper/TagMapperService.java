package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.TagDto;
import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapperService {

    TagDto toTagDto(TagEntity tagEntity);

}
