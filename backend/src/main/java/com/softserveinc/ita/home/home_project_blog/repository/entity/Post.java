package com.softserveinc.ita.home.home_project_blog.repository.entity;

import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewTagsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//IDENTITY)
    private long id;
//   private List<Tag> tags;
    private String text;
    private String title;
    private String previewAttachment;
    private User user;
//    private Date createdOn;
//    private Date updatedOn;
}
