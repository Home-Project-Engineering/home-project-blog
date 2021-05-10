package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.softserveinc.ita.homeprojectblog.util.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class PasswordDto {

  @NotBlank
  private String oldPassword;

  @Pattern(regexp = PASSWORD_REGEXP,
          message = WRONG_PASSWORD_PATTERN)
  private String newPassword;

}

