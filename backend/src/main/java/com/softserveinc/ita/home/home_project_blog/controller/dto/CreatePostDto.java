package com.softserveinc.ita.home.home_project_blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostDto {
    private Set<CreateTagDto> tags = new HashSet<>();
    private String text;
    private String title;
    private String previewAttachment;
}
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
