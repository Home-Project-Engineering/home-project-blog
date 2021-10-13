package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.data.entities.Tag;
import org.springframework.core.convert.converter.Converter;

public class TagToTagDTOConverter  implements Converter<Tag, TagDTO> {

    @Override
    public TagDTO convert(Tag source) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(source.getId());
        tagDTO.setName(source.getName());
        return tagDTO;
    }
}
