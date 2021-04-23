package com.softserveinc.ita.home.home_project_blog.dto;

import com.softserveinc.ita.home.home_project_blog.ExceptionHandling.ValidationConst;
import com.softserveinc.ita.home.home_project_blog.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class CreateUserDto {
    @Size(min = 4, max = 255, message = ValidationConst.NAME_WRONG_LENGTH)
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 1, max = 255, message = ValidationConst.FIRST_NAME_WRONG_LENGTH)
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 1, max = 255, message = ValidationConst.LAST_NAME_WRONG_LENGTH)
    @Column(name = "lastname")
    private String lastName;

    @Email(message = ValidationConst.EMAIL_IS_NOT_VALID)
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 255, message = ValidationConst.WRONG_PASSWORD)
   // @Pattern(regexp = ValidationConst.PASSWORD_PATTERN)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private User.ROLE role;
}
