package com.itacademy.blog.repository.Entity;


import com.itacademy.blog.services.DTO.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

}
