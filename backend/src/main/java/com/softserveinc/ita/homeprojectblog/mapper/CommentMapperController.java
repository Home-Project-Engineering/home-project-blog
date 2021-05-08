package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.model.Comment;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CommentMapperController {

    CommentDto toCommentDto(Comment comment);

    Comment toComment(CommentDto commentDto);

    default Page<Comment> toCommentPage(Page<CommentDto> commentDtoPage) {
        return commentDtoPage.map(this::toComment);
    }
}
