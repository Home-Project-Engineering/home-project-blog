package com.itacademy.blog.data.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.itacademy.blog.data.Entity.User;

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

    @ManyToOne
    private User author;

    private String text;

    @Column(columnDefinition = "DATE")
    private OffsetDateTime createdOn;

    @ManyToOne
    private Post post;

    @Column(columnDefinition = "DATE")
    private OffsetDateTime updatedOn;
}
