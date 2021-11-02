package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.presentationlayer.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagDTOToTagModelConverter implements BlogConverter<TagDTO, Tag> {

    @Override
    public Tag convert(TagDTO source) {
        Tag tag = new Tag();
        tag.setId(source.getId());
        tag.setName(source.getName());
        return tag;
    }
}
