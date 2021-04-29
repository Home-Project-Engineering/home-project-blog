package com.softserveinc.ita.home.home_project_blog.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//IDENTITY
    private long id;
//    private Set<Tag> tags;
    private String text;
    private String title;
//    private String previewAttachment;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
//    private Date createdOn;
//    private Date updatedOn;
}
