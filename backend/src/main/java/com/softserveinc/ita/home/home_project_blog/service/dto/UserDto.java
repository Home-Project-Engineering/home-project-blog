package com.softserveinc.ita.home.home_project_blog.service.dto;

import com.softserveinc.ita.home.home_project_blog.Validation.Const;
import com.softserveinc.ita.home.home_project_blog.repository.entity.ROLE;
import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserDto {
    private Long id;

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

    @Size(min = 8, max = 255, message = Const.WRONG_PASSWORD)
    // @Pattern(regexp = ValidationConst.PASSWORD_PATTERN)
    private String password;

    private ROLE role;

    public String getEmail() {
        return email.toLowerCase().trim();
    }
}
