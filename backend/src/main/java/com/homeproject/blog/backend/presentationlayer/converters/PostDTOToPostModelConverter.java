package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.presentationlayer.model.Author;
import com.homeproject.blog.backend.presentationlayer.model.Post;
import com.homeproject.blog.backend.presentationlayer.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class PostDTOToPostModelConverter implements BlogConverter<PostDTO, Post> {

    @Lazy
    @Autowired
    ConversionService conversionService;

    @Override
    public Post convert(PostDTO source) {
        Post post = new Post();
        post.setId(source.getId());
        post.setTags(source.getTags().stream().map(tags -> conversionService.convert(tags, Tag.class)).collect(Collectors.toList()));
        post.setAuthor(conversionService.convert(source.getAuthor(), Author.class));
        post.setCreatedOn(source.getCreatedOn());
        post.setText(source.getText());
        post.setTitle(source.getTitle());
        post.setPreviewAttachment(source.getPreviewAttachment());
        post.setUpdatedOn(source.getUpdatedOn());
        return post;
    }
}
