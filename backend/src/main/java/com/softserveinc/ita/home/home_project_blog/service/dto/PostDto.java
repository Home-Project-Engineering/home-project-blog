package com.softserveinc.ita.home.home_project_blog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class PostDto {
    private long id;
//    private List<TagDto> tags;
    private String text;
    private String title;
//    private String previewAttachment;
//    @ManyToOne()
//    @JoinColumn(name = "user_id", nullable = false)
    private UserDto user;
    //private Date createdOn;
//    private Date updatedOn;
    }
