package com.softserveinc.ita.home.home_project_blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewCommentDto {
    private Long id;
    private String text;
    private ViewAuthorDto author;
    private Date createdOn;
    private Date updatedOn;
}
