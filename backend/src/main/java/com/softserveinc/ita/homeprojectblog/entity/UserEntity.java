package com.softserveinc.ita.homeprojectblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "public")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "email")
    private String email;

    // don't use validation because already encrypted
    @Column(name = "password")
    private String password;

    @Column(name = "create_on")
    private OffsetDateTime createOn = OffsetDateTime.now();

    @Column(name = "update_on")
    private OffsetDateTime updateOn;


//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "role_id")
    @Column(name = "role_id")
//    private RoleEntity role;
    private Byte roleByte;

}
