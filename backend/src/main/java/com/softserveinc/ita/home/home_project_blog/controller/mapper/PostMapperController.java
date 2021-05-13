package com.softserveinc.ita.home.home_project_blog.controller.mapper;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreatePostDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewPostDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PostMapperController {

    PostMapperController INSTANCE = Mappers.getMapper( PostMapperController.class );

    PostDto toPostDto(CreatePostDto post);

    ViewPostDto toViewPostDto(PostDto post);

    default Page<ViewPostDto> toPageViewPostDto(Page<PostDto> posts) {
        return posts.map(this::toViewPostDto);
    }
}
