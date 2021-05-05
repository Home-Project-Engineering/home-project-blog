package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {

  private NameEnum name;

  public enum NameEnum {
    BLOGGER,

    MODERATOR,
    
    ADMIN;
  }
}

