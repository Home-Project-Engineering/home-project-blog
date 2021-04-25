package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Validated
public class UserDto {

    private BigDecimal id;

//    @Size(min=50, message="to short")
    private String name;

    private String firstName;

    private String lastName;


    private String email;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])", message = "please use pattern - a-z+A-Z+0-9")
//    @Size(min = 8, max = 255, message = "Password should be between 8 and 200 characters.")
    private String password;

//    private String createOn;

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
