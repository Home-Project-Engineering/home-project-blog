package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;
import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.repository.PostRepository;
import com.softserveinc.ita.homeprojectblog.repository.TagRepository;
import com.softserveinc.ita.homeprojectblog.service.PostService;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    PostMapperService postMapperService;
    UserMapperService userMapperService;

    PostRepository postRepository;
    TagRepository tagRepository;

    UserService userService;

    @Override
    public PostDto createPost(PostDto postDto) {

        var postEntity = postMapperService.toPostEntity(postDto);
        List<TagEntity> tags = postEntity.getTags();

        removeIdAndDuplicates(tags);

        for (var i = 0; i <tags.size(); i++) {
            Optional<TagEntity> tagOptional = tagRepository.findByName(tags.get(i).getName());
            if (tagOptional.isPresent()) {
                tags.set(i, tagOptional.get());
            } else {
                tags.get(i).setCreateOn(OffsetDateTime.now());
            }
        }

        postEntity.setCreatedOn(OffsetDateTime.now());
        postEntity.setUser(userMapperService.toUserEntity(userService.getCurrentUser()));
        postRepository.save(postEntity);

        return postMapperService.toPostDto(postEntity);
    }

    private void removeIdAndDuplicates(List<TagEntity> tags) {
        for (TagEntity tag : tags) {
            tag.setId(null);
        }

        Set<TagEntity> set = new HashSet<>(tags);
        tags.clear();
        tags.addAll(set);
    }
}
