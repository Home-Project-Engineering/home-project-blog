package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.CommentDto;
import com.softserveinc.ita.homeprojectblog.exception.PostNotMatchException;

import java.math.BigDecimal;

public interface CommentService {

    CommentDto createComment(BigDecimal postId, CommentDto commentDto);

    CommentDto getComment(BigDecimal postId, BigDecimal id) throws PostNotMatchException;
}
