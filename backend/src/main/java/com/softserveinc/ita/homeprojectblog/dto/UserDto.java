package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.softserveinc.ita.homeprojectblog.util.Constants.PASSWORD_REGEXP;
import static com.softserveinc.ita.homeprojectblog.util.Constants.WRONG_PASSWORD_PATTERN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private BigDecimal id;

    @NotBlank
    private String name;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @Pattern(regexp = PASSWORD_REGEXP,
            message = WRONG_PASSWORD_PATTERN)
    private String password;

    private OffsetDateTime createOn;

    private OffsetDateTime updatedOn;

    private RoleDto role;

}
