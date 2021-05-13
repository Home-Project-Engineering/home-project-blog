package com.softserveinc.ita.home.home_project_blog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class PostDto {
    private long id;
    private Set<TagDto> tags = new HashSet<>();
    private String text;
    private String title;
    private String previewAttachment;
    private AuthorDto author;
    private Date createdOn;
    private Date updatedOn;
}
