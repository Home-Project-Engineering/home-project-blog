package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;

import java.math.BigDecimal;

public interface CommentService {
    CommentDto createComment(BigDecimal postId, CommentDto commentDto);
}
