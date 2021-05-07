package com.example.blog.util.mappers;

import com.example.blog.generated.model.Post;
import com.example.blog.generated.model.Role;
import com.example.blog.repository.entities.PostEntity;
import com.example.blog.repository.entities.UserEntity;
import com.example.blog.util.dtos.DtoPost;
import com.example.blog.util.dtos.DtoRole;
import com.example.blog.util.dtos.DtoUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    DtoPost toDto(Post post);


    PostEntity toEntity(DtoPost dtoPost);

//    @Mapping(target = "user", source = "author")
//    @Mapping(target = "user.password", constant = "******")
    Post toModel(DtoPost dtoPost);

    Role convert(DtoRole dtoRole);

//    @Mapping(target = "role", source = "roleEntity")
    DtoUser fromEntity(UserEntity entity);

    DtoPost toDtoFromEntity(PostEntity postEntity);

    List<Post> toModels(List<DtoPost> dtoPosts);

    default Page<DtoPost> toDtoPage(Page<PostEntity> entities) {
        return entities.map(this::toDtoFromEntity);
    }

}
