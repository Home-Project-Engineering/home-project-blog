package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.services.DTO.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    @Mapping(target = "user.password", constant = "********")
    @Mapping(source = "user", target = "user")
    PostDTO convert(Post post);
    @Mapping(source = "user", target = "user")
    Post convert(PostDTO postDTO);
    List<PostDTO> convert(List<Post> postEntities);
}
