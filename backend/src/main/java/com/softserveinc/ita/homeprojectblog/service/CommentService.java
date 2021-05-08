package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.exception.PostNotMatchException;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface CommentService {

    CommentDto createComment(BigDecimal postId, CommentDto commentDto);

    CommentDto getComment(BigDecimal postId, BigDecimal id) throws PostNotMatchException;

    Page<CommentDto> getComment(BigDecimal postId, BigDecimal id, String authorName, String sort, Integer pageNum, Integer pageSize);
}
