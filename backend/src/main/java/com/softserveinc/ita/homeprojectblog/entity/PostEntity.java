package com.softserveinc.ita.homeprojectblog.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigDecimal id;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    List<TagEntity> tags;

    @Column(name = "create_on")
    OffsetDateTime createdOn;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(name = "text")
    String text;

    @Column(name = "title")
    String title;

    @Column(name = "preview_attachment")
    String previewAttachment;

    @Column(name = "update_on")
    OffsetDateTime updatedOn;

}
