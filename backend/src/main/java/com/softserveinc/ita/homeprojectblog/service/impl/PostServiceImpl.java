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
import com.softserveinc.ita.homeprojectblog.util.Checkout;
import com.softserveinc.ita.homeprojectblog.util.page.Sorter;
import com.softserveinc.ita.homeprojectblog.util.query.EntitySpecificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.softserveinc.ita.homeprojectblog.util.Constants.POST_FOR_USER_NOT_FOUND_FORMAT;
import static com.softserveinc.ita.homeprojectblog.util.Constants.POST_NOT_FOUND_FORMAT;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    PostMapperService postMapper;
    UserMapperService userMapper;

    PostRepository postRepository;
    TagRepository tagRepository;

    UserService userService;

    @Qualifier("entitySpecificationService")
    EntitySpecificationService<PostEntity> entitySpecificationService;
    Sorter sorter;
    Checkout checkout;

    @Override
    public PostDto createPost(PostDto postDto) {

        var postEntity = postMapper.toPostEntity(postDto);
        List<TagEntity> tags = postEntity.getTags();

        checkout.removeIdAndRepeatsInList(tags);
        checkout.setExistsTagsOrSetDateForNew(tagRepository, tags);

        postEntity.setCreatedOn(OffsetDateTime.now());
        postEntity.setUser(userMapper.toUserEntity(userService.getCurrentUser()));
        postRepository.save(postEntity);

        return postMapper.toPostDto(postEntity);
    }

    @Override
    public PostDto getPost(BigDecimal id) {
        var postEntityOptional = postRepository.findById(id);
        var postEntity = postEntityOptional.orElse(null);
        return postMapper.toPostDto(postEntity);
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

        var check = this.checkout.checkoutAndSetDefaults(sort, pageNum, pageSize);

        var specification =
                entitySpecificationService.getSpecification(predicateMap);
        var pageRequest = PageRequest.of(check.getPageNum(), check.getPageSize(),
                sorter.getSorter(check.getSort()));

        var postEntityPage = postRepository.findAll(specification, pageRequest);

        return postMapper.toPostDtoPage(postEntityPage);
    }

    @Override
    public void removePost(BigDecimal id) {
        var postEntity = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(POST_NOT_FOUND_FORMAT, id)));
        postRepository.deleteById(postEntity.getId());
    }

    @Override
    public PostDto updatePost(BigDecimal id, PostDto postDto) {
        var oldPostEntity = postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(POST_NOT_FOUND_FORMAT, id)));

        var newPostEntity = postMapper.toPostEntity(postDto);

        checkout.removeIdAndRepeatsInList(newPostEntity.getTags());
        checkout.setExistsTagsOrSetDateForNew(tagRepository, newPostEntity.getTags());

        newPostEntity.setUpdatedOn(OffsetDateTime.now());
        newPostEntity = postMapper.toPostEntityFromNewAndOld(newPostEntity, oldPostEntity);

        var postEntity = postRepository.save(newPostEntity);
        return postMapper.toPostDto(postEntity);
    }

    @Override
    public PostDto getPostByCurrentUser(BigDecimal id) {
        var userDto = userService.getCurrentUser();
        var postEntity = postRepository.findByUserIdAndId(userDto.getId(), id).orElseThrow(
                () -> new EntityNotFoundException(String.format(POST_FOR_USER_NOT_FOUND_FORMAT, userDto.getId(), id)));
        return postMapper.toPostDto(postEntity);
    }

    @Override
    public Page<PostDto> getPostsByCurrentUser(
            BigDecimal id, String tagId, String tagName,
            String sort, Integer pageNum, Integer pageSize) {

        var userDto = userService.getCurrentUser();

        Map<String, String> predicateMap = new HashMap<>();
        predicateMap.put("user.id", userDto.getId() != null ? userDto.getId().toString() : null);
        predicateMap.put("id", id != null ? id.toString() : null);
        predicateMap.put("tags.id", tagId);
        predicateMap.put("tags.name", tagName);

        var check = checkout.checkoutAndSetDefaults(sort, pageNum, pageSize);

        var specification = entitySpecificationService.getSpecification(predicateMap);

        var pageRequest = PageRequest.of(check.getPageNum(), check.getPageSize(),
                sorter.getSorter(check.getSort()));

        var postEntityPage = postRepository.findAll(specification, pageRequest);

        return postMapper.toPostDtoPage(postEntityPage);
    }

}
