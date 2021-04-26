package com.itacademy.blog.api.mapper;


import com.itacademy.blog.model.Post;
import com.itacademy.blog.services.DTO.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    @Mapping(source = "user", target = "user")
    PostDTO convert(Post post);

    @Mapping(target = "user.password", constant = "********")
    @Mapping(source = "user", target = "user")
    Post convert(PostDTO postDTO);
    @Mapping(target = "user.password", constant = "********")
    List<PostDTO> convert(List<Post> postEntities);
}
