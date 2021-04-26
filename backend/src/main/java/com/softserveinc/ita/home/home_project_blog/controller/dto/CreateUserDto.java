package com.softserveinc.ita.home.home_project_blog.controller.dto;

import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class CreateUserDto {
    @NotNull
    @Size(min = 4, max = 255, message = Const.NAME_WRONG_LENGTH)
    private String name;

    @Size(min = 1, max = 255, message = Const.FIRST_NAME_WRONG_LENGTH)
    private String firstName;

    @Size(min = 1, max = 255, message = Const.LAST_NAME_WRONG_LENGTH)
    private String lastName;

    @NotNull
    @Email(message = Const.EMAIL_IS_NOT_VALID)
    private String email;

    @NotNull
    @Size(min = 8, max = 255, message = Const.WRONG_PASSWORD)
    @Pattern(regexp = Const.PASSWORD_PATTERN)
    private String password;

    public String getEmail() {
        return email.toLowerCase().trim();
    }
}
