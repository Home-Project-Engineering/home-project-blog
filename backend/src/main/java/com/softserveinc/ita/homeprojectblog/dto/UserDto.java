package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.softserveinc.ita.homeprojectblog.util.Constants.*;

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
    @Size(min = 8, max = 255,
            message = WRONG_PASSWORD_SIZE)
    private String password;

    private OffsetDateTime createOn;

    private OffsetDateTime updatedOn;

    private RoleDto role;

}
