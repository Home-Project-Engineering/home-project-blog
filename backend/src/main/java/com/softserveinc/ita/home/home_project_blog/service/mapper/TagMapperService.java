package com.softserveinc.ita.home.home_project_blog.service.mapper;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TagMapperService {

    TagMapperService INSTANCE = Mappers.getMapper( TagMapperService.class );

    TagDto toTagDto(Tag tag);

    Tag toTag(TagDto tag);

    default Page<TagDto> toPageTagDto(Page<Tag> all){
        return all.map(this::toTagDto);
    }

//    Page<UserDto> toPageUserDto(Page<User> all);
}
