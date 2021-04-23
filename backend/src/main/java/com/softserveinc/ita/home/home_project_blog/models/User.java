package com.softserveinc.ita.home.home_project_blog.models;

//import io.swagger.annotations.ApiModelProperty;

import com.softserveinc.ita.home.home_project_blog.ExceptionHandling.ValidationConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Validated
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//SEQUENCE)
    private Long id;

    @Size(min = 4, max = 255, message = ValidationConst.NAME_WRONG_LENGTH)
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 1, max = 255, message = ValidationConst.FIRST_NAME_WRONG_LENGTH)
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 1, max = 255, message = ValidationConst.LAST_NAME_WRONG_LENGTH)
    @Column(name = "lastname")
    private String lastName;

    @Email(message = ValidationConst.EMAIL_IS_NOT_VALID)
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 255, message = ValidationConst.WRONG_PASSWORD)
//    @Pattern(regexp = ValidationConst.PASSWORD_PATTERN)
    private String password;

    public static enum ROLE {
        guest, user, moderator, admin, expert
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ROLE role;
}
/*
    public static enum RoleEnum {
        GUEST("guest"),
        USER("user"),
        MODERATOR("moderator"),
        ADMIN("admin"),
        EXPERT("expert");

        private String value;

        private RoleEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        @JsonCreator
        public static User.RoleEnum fromValue(String value) {
            User.RoleEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                User.RoleEnum b = var1[var3];
                if (b.value.equals(value)) {
                    return b;
                }
            }

            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }
}
*/
