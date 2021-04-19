package com.itacademy.blog.api.mapper;


import com.itacademy.blog.model.Post;
import com.itacademy.blog.services.DTO.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    PostDTO convert(Post post);
    Post convert(PostDTO postDTO);
    List<PostDTO> convert(List<Post> postEntities);
}
