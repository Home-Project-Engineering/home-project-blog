package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "author_view_convertor")
public class AuthorConverterImpl implements AuthorConverter {

    @Override
    public com.homeproject.blog.backend.presentationlayer.model.Author dtoToView(Author dto) {
        com.homeproject.blog.backend.presentationlayer.model.Author view = new com.homeproject.blog.backend.presentationlayer.model.Author();
        view.setName(dto.getName());
        view.firstName(dto.getFirstName());
        view.setLastName(dto.getSecondName());
        return view;
    }

    @Override
    public Author viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Author view) {
        if (view == null) {
            return null;
        }
        Author dto = new Author();
        dto.setName(view.getName());
        dto.setFirstName(view.getFirstName());
        dto.setSecondName(view.getLastName());
        return dto;
    }

    @Override
    public List<com.homeproject.blog.backend.presentationlayer.model.Author> dtosToViews(List<Author> dtos) {
        if (dtos == null) {
            return null;
        }
        Stream<com.homeproject.blog.backend.presentationlayer.model.Author> stream = dtos.stream().map(this::dtoToView);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Author> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Author> views) {
        if (views == null) {
            return null;
        }
        Stream<Author> stream = views.stream().map(this::viewToDTO);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
