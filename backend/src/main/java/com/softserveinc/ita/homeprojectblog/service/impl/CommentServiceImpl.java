package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.entity.CommentEntity;
import com.softserveinc.ita.homeprojectblog.exception.PostNotMatchException;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.repository.CommentRepository;
import com.softserveinc.ita.homeprojectblog.repository.PostRepository;
import com.softserveinc.ita.homeprojectblog.service.CommentService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    CommentMapperService commentMapperService;
    UserMapperService userMapperService;
    PostMapperService postMapperService;

    UserServiceImpl userService;
    PostServiceImpl postService;

    CommentRepository commentRepository;
    PostRepository postRepository;

    @Qualifier("entitySpecificationService")
    EntitySpecificationService<CommentEntity> entitySpecificationService;

    Sorter sorter;

    @Override
    public CommentDto createComment(BigDecimal postId, CommentDto commentDto) {
        var commentEntity = commentMapperService.toCommentEntity(commentDto);
        var postDto = postService.getPost(postId);
        var postEntity = postMapperService.toPostEntity(postDto);
        commentEntity.setPost(postEntity);

        var currentUser = userService.getCurrentUser();
        commentEntity.setUser(userMapperService.toUserEntity(currentUser));

        commentEntity.setCreatedOn(OffsetDateTime.now());
        var saveEntity = commentRepository.save(commentEntity);

        return commentMapperService.toCommentDto(saveEntity);
    }

    @Override
    public CommentDto getComment(BigDecimal postId, BigDecimal id) {
        var commentEntityOptional = commentRepository.findById(id);
        var commentEntity = commentEntityOptional.orElse(null);

        if (commentEntity != null) {
            BigDecimal postEntityId = commentEntity.getPost().getId();
            if (!postId.equals(postEntityId)) {
                throw new PostNotMatchException(
                        "Post id in request don't match in comment payload");
            }
        }

        return commentMapperService.toCommentDto(commentEntity);
    }

    @Override
    public Page<CommentDto> getComment(BigDecimal postId, BigDecimal id, String authorName,
                                       String sort, Integer pageNum, Integer pageSize) {

        Map<String, String> predicateMap = new HashMap<>();
        predicateMap.put("id", id != null ? id.toString() : null);
        predicateMap.put("post.id", postId != null ? postId.toString() : null);
        predicateMap.put("user.name", authorName);

        var pageNumNotNull = Optional.ofNullable(pageNum).orElse(1) - 1;
        var pageSizeNotNull = Optional.ofNullable(pageSize).orElse(10);
        var sortNotNull = Optional.ofNullable(sort).orElse("-id");

        var specification = entitySpecificationService.getSpecification(predicateMap);
        var pageRequest = PageRequest.of(pageNumNotNull, pageSizeNotNull, sorter.getSorter(sortNotNull));

        var pageEntities = commentRepository.findAll(specification, pageRequest);

        return commentMapperService.toCommentDtoPage(pageEntities);
    }
}
