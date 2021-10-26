package com.homeproject.blog.backend.businesslayer.converters;


import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.data.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagDTOToTagConverter implements BlogDTOConverter<TagDTO, Tag> {

    @Override
    public Tag convert(TagDTO source) {
        Tag tag = new Tag();
        tag.setName(source.getName());
        return tag;
    }
}
