package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {

    BigDecimal id;

    List<TagDto> tags;

    OffsetDateTime createdOn;

    UserDto user;

    String text;

    String title;

    String previewAttachment;

    OffsetDateTime updatedOn;

}
