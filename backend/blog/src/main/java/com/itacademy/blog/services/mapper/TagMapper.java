package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.services.DTO.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper( TagMapper.class );

    TagDTO convert(Tag user);
    Tag convert(TagDTO userDTO);
    List<TagDTO> convert(List<Tag> userEntities);
}
