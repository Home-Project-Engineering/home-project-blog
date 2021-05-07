package com.example.blog.backend.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserEntity author;

    private String text;

    private OffsetDateTime createdOn;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PostEntity post;

    private OffsetDateTime updatedOn;
}
