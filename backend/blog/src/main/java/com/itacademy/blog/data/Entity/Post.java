package com.itacademy.blog.data.Entity;

import com.itacademy.blog.data.Entity.User;
import com.itacademy.blog.data.Entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Tag> tags;

    @Column(columnDefinition = "DATE")
    private OffsetDateTime createdOn;

    @ManyToOne
    private User author;

    private String text;

    private String title;

    private String previewAttachment;

    @Column(columnDefinition = "DATE")
    private OffsetDateTime updatedOn;
}
