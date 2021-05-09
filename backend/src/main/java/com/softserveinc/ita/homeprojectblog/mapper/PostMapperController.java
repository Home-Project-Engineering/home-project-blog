package com.softserveinc.ita.homeprojectblog.mapper;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;
import com.softserveinc.ita.homeprojectblog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PostMapperController {

    @Mapping(target = "user", ignore = true)

    PostDto toPostDto(Post post);

    @Mapping(source = "user", target = "author")
    Post toPost(PostDto postDto);

    default Page<Post> toPostPage(Page<PostDto> postDtoPage){
        return postDtoPage.map(this::toPost);
    }
}
