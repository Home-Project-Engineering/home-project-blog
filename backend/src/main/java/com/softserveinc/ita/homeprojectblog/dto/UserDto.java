package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private BigDecimal id;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

//    private String createTime;

    /**
     * This is the level of user access to various functions
     */
    public enum RoleEnum {
        GUEST("guest"),

        USER("user"),

        MODERATOR("moderator"),

        ADMIN("admin"),

        EXPERT("expert");

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }

    private com.softserveinc.ita.homeprojectblog.entity.UserEntity.RoleEnum role;
}
