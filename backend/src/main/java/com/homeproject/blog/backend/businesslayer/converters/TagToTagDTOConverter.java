package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.data.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagToTagDTOConverter implements BlogDTOConverter<Tag, TagDTO> {

    @Override
    public TagDTO convert(Tag source) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(source.getId());
        tagDTO.setName(source.getName());
        return tagDTO;
    }
}
