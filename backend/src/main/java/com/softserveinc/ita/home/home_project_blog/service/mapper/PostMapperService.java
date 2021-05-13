package com.softserveinc.ita.home.home_project_blog.service.mapper;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PostMapperService {

    PostMapperService INSTANCE = Mappers.getMapper(PostMapperService.class);

    PostDto toPostDto(Post post);

    Post toPost(PostDto post);

    default Page<PostDto> toPagePostDto(Page<Post> all) {
        return all.map(this::toPostDto);
    }
}
