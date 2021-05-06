package com.softserveinc.ita.home.home_project_blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewPostDto {
    private Long id;
    private Set<ViewTagDto> tags = new HashSet<>();
    private String text;
    private String title;
    private String previewAttachment;
    private ViewUserForPostDto user;
    private Date createdOn;
    private Date updatedOn;
}
