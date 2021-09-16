package com.softserveinc.ita.homeprojectblog.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleDto {

  Long id;

  NameEnum name;

  public enum NameEnum {
    BLOGGER,

    MODERATOR,
    
    ADMIN
  }

}
