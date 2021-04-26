package com.softserveinc.ita.home.home_project_blog.repository.entity;

//import io.swagger.annotations.ApiModelProperty;

import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Validated
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 4, max = 255, message = Const.NAME_WRONG_LENGTH)
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 1, max = 255, message = Const.FIRST_NAME_WRONG_LENGTH)
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 1, max = 255, message = Const.LAST_NAME_WRONG_LENGTH)
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Email(message = Const.EMAIL_IS_NOT_VALID)
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 255, message = Const.WRONG_PASSWORD)
    @Column(nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role role;
}