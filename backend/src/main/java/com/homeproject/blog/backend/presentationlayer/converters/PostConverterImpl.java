package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "post_view_convertor")
public class PostConverterImpl implements PostConverter {
    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private TagConverter tagConverter;

    @Override
    public com.homeproject.blog.backend.presentationlayer.model.Post dtoToView(Post dto) {
        com.homeproject.blog.backend.presentationlayer.model.Post view = new com.homeproject.blog.backend.presentationlayer.model.Post();
        view.setId(new BigDecimal(dto.getId()));
        view.setAuthor(authorConverter.dtoToView(dto.getAuthor()));
        view.setCreatedOn(OffsetDateTime.parse(dto.getCreatedOn()));
        view.setTags(tagConverter.dtosToViews(dto.getTags()));
        view.setText(dto.getText());
        view.setTitle(dto.getTitle());
        view.setPreviewAttachment(dto.getPreviewAttachment());
        view.setUpdatedOn(OffsetDateTime.parse(dto.getUpdatedOn()));
        return view;
    }

    @Override
    public Post viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Post view) {
        Post post = new Post();
        Long id = view.getId() == null ? null : view.getId().longValue();
        post.setId(id);
        post.setAuthor(authorConverter.viewToDTO(view.getAuthor()));
        String createdOn = view.getCreatedOn() == null ? null : view.getCreatedOn().toString();
        post.setCreatedOn(createdOn);
        post.setTags(tagConverter.viewsToDTOs(view.getTags()));
        post.setText(view.getText());
        post.setTitle(view.getTitle());
        post.setPreviewAttachment(view.getPreviewAttachment());
        String updatedOn = view.getUpdatedOn() == null ? null : view.getUpdatedOn().toString();
        post.setUpdatedOn(updatedOn);
        return post;
    }

    @Override
    public List<com.homeproject.blog.backend.presentationlayer.model.Post> dtosToViews(List<Post> dtos) {
        if (dtos == null) {
            return null;
        }
        Stream<com.homeproject.blog.backend.presentationlayer.model.Post> stream = dtos.stream().map(this::dtoToView);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Post> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Post> views) {
        if (views == null) {
            return null;
        }
        Stream<Post> stream = views.stream().map(this::viewToDTO);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}