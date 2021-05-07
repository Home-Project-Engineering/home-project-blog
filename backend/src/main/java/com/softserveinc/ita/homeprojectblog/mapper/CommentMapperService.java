package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapperService {

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "user", ignore = true)
    CommentEntity toCommentEntity(CommentDto commentDto);

    @Mapping(target = "author", source = "user")
    CommentDto toCommentDto(CommentEntity saveEntity);
}
