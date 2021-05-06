package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;
import com.softserveinc.ita.homeprojectblog.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapperService {

    PostEntity toPostEntity(PostDto postDto);

    PostDto toPostDto(PostEntity postEntity);

}
