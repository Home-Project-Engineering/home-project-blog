package com.softserveinc.ita.home.home_project_blog.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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
    @ManyToMany//(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "posts_tags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags = new HashSet<>();
    private String text;
    private String title;
    private String previewAttachment;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
//    private Date createdOn;
//    private Date updatedOn;
}
