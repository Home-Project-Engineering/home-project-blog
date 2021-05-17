package com.softserveinc.ita.home.home_project_blog.service.dto;

import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordBody {
    private String oldPassword;

    @NotNull
    @Pattern(regexp = Const.PASSWORD_PATTERN, message = Const.WRONG_PASSWORD)
    private String newPassword;
}
