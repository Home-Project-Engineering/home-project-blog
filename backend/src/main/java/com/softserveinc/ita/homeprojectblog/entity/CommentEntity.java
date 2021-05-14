package com.softserveinc.ita.homeprojectblog.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigDecimal id;

    @Column(name = "text")
    String text;

    @ManyToOne(cascade = {CascadeType.MERGE})
    PostEntity post;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(name = "create_on")
    OffsetDateTime createdOn;

    @Column(name = "update_on")
    OffsetDateTime updatedOn;

}
