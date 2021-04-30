package com.softserveinc.ita.home.home_project_blog.controller.mapper;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreateTagDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewTagDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TagMapperController {

    TagMapperController INSTANCE = Mappers.getMapper( TagMapperController.class );

    //@Mapping(target = "role", constant = ROLE.BLOGGER.name())
    TagDto toTagDto(CreateTagDto tag);

    ViewTagDto toViewTagDto(TagDto tag);

    List<ViewTagDto> toViewTagDtos(List<TagDto> tags);

    default Page<ViewTagDto> toPageViewTagDto(Page<TagDto> tags) {
        return tags.map(this::toViewTagDto);
    }
}
