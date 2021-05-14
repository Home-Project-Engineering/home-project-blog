package com.softserveinc.ita.homeprojectblog.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigDecimal id;

    @NotBlank
    @Column(name = "name")
    String name;

    @NotBlank
    @Column(name = "first_name")
    String firstName;

    @NotBlank
    @Column(name = "last_name")
    String lastName;

    @NotBlank
    @Column(name = "email")
    String email;

    // don't use validation because already encrypted
    @Column(name = "password")
    String password;

    @Column(name = "create_on")
    OffsetDateTime createOn;

    @Column(name = "update_on")
    OffsetDateTime updatedOn;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    RoleEntity role;

}
