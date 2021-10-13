package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import java.util.stream.Collectors;


public class PostDTOToPostConverter implements Converter<PostDTO, Post> {
    @Autowired
    ConversionService conversionService;

    @Override
    public Post convert(PostDTO source) {
        Post post = new Post();
        post.setTags(source.getTags().stream().map(tags -> conversionService.convert(tags, Tag.class)).collect(Collectors.toList()));
        post.setText(source.getText());
        post.setTitle(source.getTitle());
        post.setPreviewAttachment(source.getPreviewAttachment());
        return post;
    }
}
