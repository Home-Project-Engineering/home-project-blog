package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class PostToPostDTOConverter implements Converter<Post, PostDTO> {

    @Autowired
    ConversionService conversionService;

    @Override
    public PostDTO convert(Post source){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(source.getId());
        postDTO.setTags(source.getTags().stream().map(tags -> conversionService.convert(tags, TagDTO.class)).collect(Collectors.toList()));
        postDTO.setAuthor(conversionService.convert(source.getAuthor(), UserDTO.class));
        postDTO.setCreatedOn(source.getCreatedOn());
        postDTO.setText(source.getText());
        postDTO.setTitle(source.getTitle());
        postDTO.setPreviewAttachment(source.getPreviewAttachment());
        postDTO.setUpdatedOn(source.getUpdatedOn());
        return postDTO;
    }
}
