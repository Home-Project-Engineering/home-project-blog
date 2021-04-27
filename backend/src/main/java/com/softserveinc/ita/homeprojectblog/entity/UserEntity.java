package com.softserveinc.ita.homeprojectblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.*;

import static com.softserveinc.ita.homeprojectblog.util.Constants.*;

//import org.threeten.bp.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "email")
    private String email;

    @Pattern(regexp = PASSWORD_REGEXP,
            message = WRONG_PASSWORD_PATTERN)
    @Size(min = 8, max = 255,
            message = WRONG_PASSWORD_SIZE)
    @Column(name = "password")
    private String password;

    @Column(name = "create_on")
    private OffsetDateTime createOn = OffsetDateTime.now();

    @Column(name = "update_on")
    private OffsetDateTime updateOn;

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

    @Column(name = "role")
    private UserEntity.RoleEnum role;
}
