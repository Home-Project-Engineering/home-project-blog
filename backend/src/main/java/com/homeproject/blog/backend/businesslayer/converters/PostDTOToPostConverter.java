package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Tag;
import com.homeproject.blog.backend.data.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostDTOToPostConverter implements BlogDTOConverter<PostDTO, Post> {

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Post convert(PostDTO source) {
        Post post = new Post();
        post.setTags(source.getTags().stream().map(tag -> tagRepository.findByName(tag.getName()).orElse(conversionService.convert(tag, Tag.class))).collect(Collectors.toList()));
        post.setText(source.getText());
        post.setTitle(source.getTitle());
        post.setPreviewAttachment(source.getPreviewAttachment());
        return post;
    }
}
