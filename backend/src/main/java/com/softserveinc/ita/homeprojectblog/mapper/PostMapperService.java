package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;
import com.softserveinc.ita.homeprojectblog.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PostMapperService {

    PostEntity toPostEntity(PostDto postDto);

    PostDto toPostDto(PostEntity postEntity);

    default Page<PostDto> toPostDtoPage(Page<PostEntity> postEntityPage){
        return postEntityPage.map(this::toPostDto);
    }

    @Mapping(target = "id", source = "oldPostEntity.id")
    @Mapping(target = "createdOn", source = "oldPostEntity.createdOn")
    @Mapping(target = "user", source = "oldPostEntity.user")
    @Mapping(target = "tags", source = "newPostEntity.tags")
    @Mapping(target = "text", source = "newPostEntity.text")
    @Mapping(target = "title", source = "newPostEntity.title")
    @Mapping(target = "previewAttachment", source = "newPostEntity.previewAttachment")
    @Mapping(target = "updatedOn", source = "newPostEntity.updatedOn")
    PostEntity toPostEntityFromNewAndOld(PostEntity newPostEntity, PostEntity oldPostEntity);

}
