package com.softserveinc.ita.home.home_project_blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewPostDto {

    private Long id;
//    private List<ViewTagDto> tags;
    private String text;
    private String title;
//    private String previewAttachment;
    private ViewUserDto user;
//    private Date createdOn;
//    private Date updatedOn;
}
//    {
//        "id": 0,
//            "tags": [
//        {
//            "id": 0,
//                "name": "Java8"
//        }
//  ],
//        "createdOn": "2017-07-21T17:32:28Z",
//            "user": {
//        "id": 0,
//                "name": "John78",
//                "firstName": "John",
//                "lastName": "Smith",
//                "email": "john.smith@example.com",
//                "password": "passworD321",
//                "role": {
//            "name": "blogger"
//        }
//    },
//        "text": "string",
//            "title": "string",
//            "previewAttachment": "string",
//            "updatedOn": "2017-07-21T17:32:28Z"


