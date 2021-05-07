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
@Table(name = "comment", schema = "public")
public class CommentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private PostEntity post;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "create_on")
    private OffsetDateTime createdOn;

    @Column(name = "update_on")
    private OffsetDateTime updatedOn;

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id + "\n" +
                ", text='" + text + '\'' + "\n" +
                ", post=" + post + "\n" +
                ", user=" + user + "\n" +
                ", createdOn=" + createdOn + "\n" +
                ", updatedOn=" + updatedOn + "\n" +
                '}';
    }
}
