package com.softserveinc.ita.homeprojectblog.dto;

import com.softserveinc.ita.homeprojectblog.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
  private BigDecimal id;

  private Author author;

  private String text;

  private OffsetDateTime createdOn;

  private OffsetDateTime updatedOn;


}



