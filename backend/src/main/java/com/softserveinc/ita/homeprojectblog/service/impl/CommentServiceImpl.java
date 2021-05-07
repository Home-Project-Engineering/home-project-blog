package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.exception.PostNotMatchException;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.repository.CommentRepository;
import com.softserveinc.ita.homeprojectblog.repository.PostRepository;
import com.softserveinc.ita.homeprojectblog.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
}
