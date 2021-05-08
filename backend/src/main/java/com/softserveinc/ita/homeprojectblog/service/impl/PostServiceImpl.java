package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.PostDto;
import com.softserveinc.ita.homeprojectblog.entity.PostEntity;
import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.repository.PostRepository;
import com.softserveinc.ita.homeprojectblog.repository.TagRepository;
import com.softserveinc.ita.homeprojectblog.service.PostService;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import com.softserveinc.ita.homeprojectblog.util.page.Checkout;
import com.softserveinc.ita.homeprojectblog.util.page.Sorter;
import com.softserveinc.ita.homeprojectblog.util.query.EntitySpecificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    Checkout checkout;
    @Qualifier("entitySpecificationService")
    EntitySpecificationService<PostEntity> entitySpecificationService;
    Sorter sorter;

    @Override
    public PostDto createPost(PostDto postDto) {

        var postEntity = postMapperService.toPostEntity(postDto);
        List<TagEntity> tags = postEntity.getTags();

        removeIdAndDuplicates(tags);

        for (var i = 0; i < tags.size(); i++) {
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

    @Override
    public PostDto getPost(BigDecimal id) {
        var postEntityOptional = postRepository.findById(id);
        var postEntity = postEntityOptional.orElse(null);
        return postMapperService.toPostDto(postEntity);
    }

    @Override
    public Page<PostDto> getPosts(
            BigDecimal id, String tagId, String tagName, String authorName,
            String sort, Integer pageNum, Integer pageSize) {

        Map<String, String> predicateMap = new HashMap<>();
        predicateMap.put("id", id != null ? id.toString() : null);
        predicateMap.put("tags.id", tagId);
        predicateMap.put("tags.name", tagName);
        predicateMap.put("user.name", authorName);

        var check = checkout.checkoutAndSetDefaults(sort, pageNum, pageSize);

        var specification =
                entitySpecificationService.getSpecification(predicateMap);
        var pageRequest = PageRequest.of(check.getPageNum(), check.getPageSize(),
                sorter.getSorter(check.getSort()));

        var postEntityPage = postRepository.findAll(specification, pageRequest);

        return postMapperService.toPostDtoPage(postEntityPage);
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
