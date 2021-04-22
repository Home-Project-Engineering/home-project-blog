package com.softserveinc.ita.home.home_project_blog.dto;

import com.softserveinc.ita.home.home_project_blog.models.User;
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
public class UpdateUserDto {
    @Min(4)
    @NotNull
    private String name;
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;
}
