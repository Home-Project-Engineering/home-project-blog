package com.homeproject.blog.backend.presentationlayer.converters;


import com.homeproject.blog.backend.dtos.Author;

import java.util.List;

public interface AuthorConverter {

    com.homeproject.blog.backend.presentationlayer.model.Author dtoToView(Author dto);

    Author viewToDTO(com.homeproject.blog.backend.presentationlayer.model.Author view);

    List<com.homeproject.blog.backend.presentationlayer.model.Author> dtosToViews(List<Author> dtos);

    List<Author> viewsToDTOs(List<com.homeproject.blog.backend.presentationlayer.model.Author> views);
}
