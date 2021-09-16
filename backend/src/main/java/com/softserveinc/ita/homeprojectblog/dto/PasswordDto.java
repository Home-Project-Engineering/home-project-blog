package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.softserveinc.ita.homeprojectblog.util.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordDto {

    @NotBlank
    String oldPassword;

    @Pattern(regexp = PASSWORD_REGEXP,
            message = WRONG_PASSWORD_PATTERN)
    String newPassword;

}

