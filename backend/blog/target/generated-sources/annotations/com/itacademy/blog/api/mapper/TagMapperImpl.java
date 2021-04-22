package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.Tag;
import com.itacademy.blog.services.DTO.TagDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-22T13:30:44+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDTO convert(Tag user) {
        if ( user == null ) {
            return null;
        }

        TagDTO tagDTO = new TagDTO();

        tagDTO.setId( user.getId() );
        tagDTO.setName( user.getName() );

        return tagDTO;
    }

    @Override
    public Tag convert(TagDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( userDTO.getId() );
        tag.setName( userDTO.getName() );

        return tag;
    }

    @Override
    public List<TagDTO> convert(List<Tag> userEntities) {
        if ( userEntities == null ) {
            return null;
        }

        List<TagDTO> list = new ArrayList<TagDTO>( userEntities.size() );
        for ( Tag tag : userEntities ) {
            list.add( convert( tag ) );
        }

        return list;
    }
}
