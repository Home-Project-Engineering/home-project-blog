package com.example.blog.util.dtos;

import com.example.blog.generated.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoComment {
    private BigDecimal id;

    private DtoUser author;

    private String text;

    private OffsetDateTime createdOn;

    private Post post;

    private OffsetDateTime updatedOn;
}
