package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.presentationlayer.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagModelToTagDTOConverter implements BlogConverter<Tag, TagDTO> {
    @Override
    public TagDTO convert(Tag source) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(source.getName());
        return tagDTO;
    }
}
