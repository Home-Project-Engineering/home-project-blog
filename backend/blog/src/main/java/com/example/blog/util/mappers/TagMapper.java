package com.example.blog.backend.util.mappers;

import com.example.blog.backend.generated.model.Tag;
import com.example.blog.backend.repository.entities.TagEntity;
import com.example.blog.backend.util.dtos.DtoTag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag toModel(DtoTag dtoTag);

    DtoTag toDtoFromEntity(TagEntity entity);

    List<Tag> toModels(List<DtoTag> dtoTags);

    default Page<DtoTag> toPageDto(Page<TagEntity> pageEntity) {
        return pageEntity.map(this::toDtoFromEntity);
    }
}
