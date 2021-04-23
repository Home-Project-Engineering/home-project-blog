package com.itacademy.blog.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private String text;

    @Column(columnDefinition = "DATE")
    private OffsetDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @Column(columnDefinition = "DATE")
    private OffsetDateTime updatedOn;
}
