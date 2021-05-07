package com.example.blog.backend.util.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPost {

    private BigDecimal id;

    private List<DtoTag> tags;

    private OffsetDateTime createdOn;

    private DtoUser author;

    private String text;

    private String title;

    private String previewAttachment;

    private OffsetDateTime updatedOn;
}
