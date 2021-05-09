package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.entity.CommentEntity;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.repository.CommentRepository;
import com.softserveinc.ita.homeprojectblog.service.CommentService;
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
import java.util.Map;

import static com.softserveinc.ita.homeprojectblog.util.Constants.COMMENT_FOR_POST_NOT_FOUND_FORMAT;
import static com.softserveinc.ita.homeprojectblog.util.Constants.COMMENT_FOR_USER_NOT_FOUND_FORMAT;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    CommentMapperService commentMapper;
    UserMapperService userMapper;
    PostMapperService postMapper;

    UserServiceImpl userService;
    PostServiceImpl postService;

    CommentRepository commentRepository;

    @Qualifier("entitySpecificationService")
    EntitySpecificationService<CommentEntity> entitySpecificationService;

    Sorter sorter;
    Checkout checkout;

    @Override
    public CommentDto createComment(BigDecimal postId, CommentDto commentDto) {
        var commentEntity = commentMapper.toCommentEntity(commentDto);
        var postDto = postService.getPost(postId);
        var postEntity = postMapper.toPostEntity(postDto);
        commentEntity.setPost(postEntity);

        var currentUser = userService.getCurrentUser();
        commentEntity.setUser(userMapper.toUserEntity(currentUser));

        commentEntity.setCreatedOn(OffsetDateTime.now());
        var saveEntity = commentRepository.save(commentEntity);

        return commentMapper.toCommentDto(saveEntity);
    }

    @Override
    public CommentDto getComment(BigDecimal postId, BigDecimal id) {
        var commentEntityOptional = commentRepository.findOneByPostIdAndId(postId, id);
        var commentEntity = commentEntityOptional.orElseThrow(() -> new EntityNotFoundException(
                String.format(COMMENT_FOR_POST_NOT_FOUND_FORMAT, id, postId)));

        return commentMapper.toCommentDto(commentEntity);
    }

    @Override
    public Page<CommentDto> getComment(BigDecimal postId, BigDecimal id, String authorName,
                                       String sort, Integer pageNum, Integer pageSize) {

        Map<String, String> predicateMap = new HashMap<>();
        predicateMap.put("id", id != null ? id.toString() : null);
        predicateMap.put("post.id", postId != null ? postId.toString() : null);
        predicateMap.put("user.name", authorName);

        var check = checkout.checkoutAndSetDefaults(sort, pageNum, pageSize);

        var specification = entitySpecificationService.getSpecification(predicateMap);
        var pageRequest = PageRequest.of(check.getPageNum(), check.getPageSize(),
                sorter.getSorter(check.getSort()));

        var pageEntities = commentRepository.findAll(specification, pageRequest);

        return commentMapper.toCommentDtoPage(pageEntities);
    }

    @Override
    public void removeComment(BigDecimal postId, BigDecimal id) {
        var commentEntity = commentRepository.findOneByPostIdAndId(postId, id).orElseThrow(
                () -> new EntityNotFoundException(String.format(COMMENT_FOR_POST_NOT_FOUND_FORMAT, id, postId)));

        commentRepository.deleteById(commentEntity.getId());
    }

    @Override
    public CommentDto updateComment(BigDecimal postId, BigDecimal id, CommentDto commentDto) {
        var oldCommentEntity = commentRepository.findOneByPostIdAndId(postId, id).orElseThrow(
                () -> new EntityNotFoundException(String.format(COMMENT_FOR_POST_NOT_FOUND_FORMAT, id, postId)));
        oldCommentEntity.setText(commentDto.getText());
        oldCommentEntity.setUpdatedOn(OffsetDateTime.now());
        var newCommentEntity = commentRepository.save(oldCommentEntity);
        return commentMapper.toCommentDto(newCommentEntity);
    }

    @Override
    public CommentDto getCommentByCurrentUser(BigDecimal id) {
        var userDto = userService.getCurrentUser();
        var commentEntity = commentRepository.findByUserIdAndId(userDto.getId(),id)
                .orElseThrow(()-> new EntityNotFoundException(
                        String.format(COMMENT_FOR_USER_NOT_FOUND_FORMAT, userDto.getId(), id)));
        return commentMapper.toCommentDto(commentEntity);
    }
}
