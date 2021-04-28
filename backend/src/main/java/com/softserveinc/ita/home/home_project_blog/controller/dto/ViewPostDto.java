package com.softserveinc.ita.home.home_project_blog.controller.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ViewPostDto {
    private long id;
    private List<ViewTagsDto> tags;
    private String text;
    private String title;
    private String previewAttachment;
    private String userName;
    private Date createdOn;
    private Date updatedOn;
//    {
//        "tags": [
//        {
//            "name": "Java8"
//        }
//  ],
//        "text": "string",
//            "title": "string",
//            "previewAttachment": "string"
//    }
}

