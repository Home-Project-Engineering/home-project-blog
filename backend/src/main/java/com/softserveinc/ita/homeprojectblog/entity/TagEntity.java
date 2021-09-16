package com.softserveinc.ita.homeprojectblog.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "create_on")
    OffsetDateTime createOn;

    @Column(name = "update_on")
    OffsetDateTime updateOn;

}
