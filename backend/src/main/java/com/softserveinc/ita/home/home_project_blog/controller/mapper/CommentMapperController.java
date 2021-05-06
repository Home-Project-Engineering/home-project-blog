package com.softserveinc.ita.home.home_project_blog.controller.mapper;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreateCommentDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewCommentDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapperController {

    CommentMapperController INSTANCE = Mappers.getMapper( CommentMapperController.class );

    //@Mapping(target = "role", constant = ROLE.BLOGGER.name())
    CommentDto toCommentDto(CreateCommentDto comment);

    ViewCommentDto toViewCommentDto(CommentDto comment);

    List<ViewCommentDto> toViewCommentDtos(List<CommentDto> comments);

    default Page<ViewCommentDto> toPageViewCommentDto(Page<CommentDto> comments) {
        return comments.map(this::toViewCommentDto);
    }
}
