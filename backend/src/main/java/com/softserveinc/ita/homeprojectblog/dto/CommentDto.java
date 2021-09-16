package com.softserveinc.ita.homeprojectblog.dto;

import com.softserveinc.ita.homeprojectblog.model.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {

    BigDecimal id;

    Author author;

    String text;

    OffsetDateTime createdOn;

    OffsetDateTime updatedOn;

}



