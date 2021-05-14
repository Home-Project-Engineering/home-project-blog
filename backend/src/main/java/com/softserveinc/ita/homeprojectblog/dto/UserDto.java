package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.softserveinc.ita.homeprojectblog.util.Constants.PASSWORD_REGEXP;
import static com.softserveinc.ita.homeprojectblog.util.Constants.WRONG_PASSWORD_PATTERN;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    BigDecimal id;

    @NotBlank
    String name;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String email;

    @Pattern(regexp = PASSWORD_REGEXP,
            message = WRONG_PASSWORD_PATTERN)
    String password;

    OffsetDateTime createOn;

    OffsetDateTime updatedOn;

    RoleDto role;

}
