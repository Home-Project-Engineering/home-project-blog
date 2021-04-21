package com.softserveinc.ita.home.home_project_blog.dto;

import com.softserveinc.ita.home.home_project_blog.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class CreateUserDto {
    @Min(4) @NotNull
    private String name;
    private String firstName;
    private String lastName;
    @Email @NotNull
    private String email;
    @NotNull
    @Size(min = 8, max = 255, message = "Password should be between 8 and 200 characters.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])")
    private String password;
    @Valid
    private User.ROLE role;
}
