package com.softserveinc.ita.homeprojectblog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    private List<TagEntity> tags;

    @Column(name = "create_on")
    private OffsetDateTime createdOn;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "text")
    private String text;

    @Column(name = "title")
    private String title;

    @Column(name = "preview_attachment")
    private String previewAttachment;

    @Column(name = "update_on")
    private OffsetDateTime updatedOn;

}
