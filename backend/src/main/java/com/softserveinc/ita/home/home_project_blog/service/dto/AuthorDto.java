package com.softserveinc.ita.home.home_project_blog.service.dto;

import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class AuthorDto {
    private Long id;

    @NotNull
    @Size(min = 4, max = 255, message = Const.NAME_WRONG_LENGTH)
    private String name;

    @Size(min = 1, max = 255, message = Const.FIRST_NAME_WRONG_LENGTH)
    private String firstName;

    @Size(min = 1, max = 255, message = Const.LAST_NAME_WRONG_LENGTH)
    private String lastName;

}
