package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface CommentService {

    CommentDto createComment(BigDecimal postId, CommentDto commentDto);

    CommentDto getComment(BigDecimal postId, BigDecimal id);

    Page<CommentDto> getComment(BigDecimal postId, BigDecimal id, String authorName, String sort, Integer pageNum, Integer pageSize);

    void removeComment(BigDecimal postId, BigDecimal id);

    CommentDto updateComment(BigDecimal postId, BigDecimal id, CommentDto commentDto);

    CommentDto getCommentByCurrentUser(BigDecimal id);

    Page<CommentDto> getCommentsByCurrentUser(BigDecimal id, String sort, Integer pageNum, Integer pageSize);
}
