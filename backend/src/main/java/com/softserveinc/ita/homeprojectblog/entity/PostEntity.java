package com.softserveinc.ita.homeprojectblog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post", schema = "public")
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "title")
    private String title;

    @Column(name = "preview_attachment")
    private String previewAttachment;

    @OneToOne
//    @Column(name = "tags_id")
    private TagEntity tag;

    @ManyToOne
//    @Column(name = "user_id")
    private UserEntity user;

    @Column(name = "create_on")
    private OffsetDateTime createOn;

    @Column(name = "text")
    private String text;

    @Column(name = "update_on")
    private OffsetDateTime updateOn;

}
