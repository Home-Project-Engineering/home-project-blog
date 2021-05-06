package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private BigDecimal id;

    private List<TagDto> tags;

    private OffsetDateTime createdOn;

    private UserDto user;

    private String text;

    private String title;

    private String previewAttachment;

    private OffsetDateTime updatedOn;

}
