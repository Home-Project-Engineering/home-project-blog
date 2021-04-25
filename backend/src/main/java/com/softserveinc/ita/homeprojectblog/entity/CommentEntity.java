package com.softserveinc.ita.homeprojectblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threeten.bp.OffsetDateTime;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comment", schema = "public")
public class CommentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
//    @Column(name = "post_id")
    private PostEntity post;

    @OneToOne
//    @Column(name = "user_id")
    private UserEntity user;

    @Column(name = "create_on")
    private OffsetDateTime createdOn;

    @Column(name = "update_on")
    private OffsetDateTime updatedOn;
}
