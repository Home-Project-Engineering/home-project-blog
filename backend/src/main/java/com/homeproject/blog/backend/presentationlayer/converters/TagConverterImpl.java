package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.Tag;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "tag_view_convertor")
public class TagConverterImpl implements TagConverter {

    @Override
    public com.homeproject.blog.backend.presentationlayer.model.Tag dtoToView(Tag dto) {
        com.homeproject.blog.backend.presentationlayer.model.Tag view = new com.homeproject.blog.backend.presentationlayer.model.Tag();
        view.setId(new Long(dto.getId()));
        view.setName(dto.getName());
        return view;
    }

    @Override
    public Tag viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Tag view) {
        Tag dto = new Tag();
        Long id = view.getId() == null ? null : view.getId().longValue();
        dto.setId(id);
        dto.setName(view.getName());
        return dto;
    }

    @Override
    public List<com.homeproject.blog.backend.presentationlayer.model.Tag> dtosToViews(List<Tag> dtos) {
        if (dtos == null) {
            return null;
        }
        Stream<com.homeproject.blog.backend.presentationlayer.model.Tag> stream = dtos.stream().map(this::dtoToView);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Tag> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Tag> views) {
        if (views == null) {
            return null;
        }
        Stream<Tag> stream = views.stream().map(this::viewToDTO);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
