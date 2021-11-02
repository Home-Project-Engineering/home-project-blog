package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.dtos.Tag;

import java.util.List;

public interface TagConverter {

    com.homeproject.blog.backend.presentationlayer.model.Tag dtoToView(Tag dto);

    Tag viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Tag view);

    List<com.homeproject.blog.backend.presentationlayer.model.Tag> dtosToViews(List<Tag> dtos);

    List<Tag> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Tag> views);
}
