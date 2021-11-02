package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.Comment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "comment_view_convertor")
public class CommentConverterImpl implements CommentConverter {
    AuthorConverterImpl authorConverter = new AuthorConverterImpl();

    @Override
    public com.homeproject.blog.backend.presentationlayer.model.Comment dtoToView(Comment dto) {
        com.homeproject.blog.backend.presentationlayer.model.Comment view = new com.homeproject.blog.backend.presentationlayer.model.Comment();
        view.setId(new Long(dto.getId()));
        view.setText(dto.getText());
        view.setAuthor(authorConverter.dtoToView(dto.getAuthor()));
        view.setCreatedOn(OffsetDateTime.parse(dto.getCreatedOn()));
        view.setUpdatedOn(OffsetDateTime.parse(dto.getUpdatedOn()));
        return view;
    }

    @Override
    public Comment viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Comment view) {
        Comment dto = new Comment();
        Long id = view.getId() == null ? null : view.getId().longValue();
        dto.setId(id);
        String createdOn = view.getCreatedOn() == null ? null : view.getCreatedOn().toString();
        dto.setCreatedOn(createdOn);
        dto.setText(view.getText());
        String updatedOn = view.getUpdatedOn() == null ? null : view.getUpdatedOn().toString();
        dto.setUpdatedOn(updatedOn);
        return dto;
    }

    @Override
    public List<com.homeproject.blog.backend.presentationlayer.model.Comment> dtosToViews(List<Comment> dtos) {
        if (dtos == null) {
            return null;
        }
        Stream<com.homeproject.blog.backend.presentationlayer.model.Comment> stream = dtos.stream().map(this::dtoToView);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Comment> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Comment> views) {
        if (views == null) {
            return null;
        }
        Stream<Comment> stream = views.stream().map(this::viewToDTO);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
