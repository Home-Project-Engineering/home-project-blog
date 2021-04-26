package com.softserveinc.ita.home.blog.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private BigDecimal id;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


    public enum RoleEnum {
        GUEST("guest"),

        USER("user"),

        MODERATOR("moderator"),

        ADMIN("admin"),

        EXPERT("expert");

        private final String value;

        RoleEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static RoleEnum fromValue(String value) {
            for (RoleEnum b : RoleEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private RoleEnum role;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO user = (UserDTO) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.name, user.name) &&
                Objects.equals(this.firstName, user.firstName) &&
                Objects.equals(this.lastName, user.lastName) &&
                Objects.equals(this.email, user.email) &&
                Objects.equals(this.password, user.password) &&
                Objects.equals(this.role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, firstName, lastName, email, password, role);
    }


}
