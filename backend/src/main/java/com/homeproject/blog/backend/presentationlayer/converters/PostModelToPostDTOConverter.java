package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.presentationlayer.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostModelToPostDTOConverter implements BlogConverter<Post, PostDTO> {

    @Lazy
    @Autowired
    ConversionService conversionService;

    @Override
    public PostDTO convert(Post source) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTags(source.getTags().stream().map(tags -> conversionService.convert(tags, TagDTO.class)).collect(Collectors.toList()));
        postDTO.setText(source.getText());
        postDTO.setTitle(source.getTitle());
        postDTO.setPreviewAttachment(source.getPreviewAttachment());
        return postDTO;
    }
}
