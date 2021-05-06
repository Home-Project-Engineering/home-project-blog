package com.softserveinc.ita.homeprojectblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tag", schema = "public")
public class TagEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_on")
    private OffsetDateTime createOn;

    @Column(name = "update_on")
    private OffsetDateTime updateOn;

}
