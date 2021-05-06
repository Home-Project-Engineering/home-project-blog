package com.softserveinc.ita.home.home_project_blog.service.mapper;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Comment;
import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CommentMapperService {

    CommentMapperService INSTANCE = Mappers.getMapper( CommentMapperService.class );

    CommentDto toCommentDto(Comment comment);

    Comment toComment(CommentDto comment);

    default Page<CommentDto> toPageCommentDto(Page<Comment> all){
        return all.map(this::toCommentDto);
    }

//    Page<UserDto> toPageUserDto(Page<User> all);
}
