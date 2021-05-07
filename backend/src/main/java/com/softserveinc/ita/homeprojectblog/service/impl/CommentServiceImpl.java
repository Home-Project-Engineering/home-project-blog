package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.mapper.CommentMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.PostMapperService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.repository.CommentRepository;
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
}
