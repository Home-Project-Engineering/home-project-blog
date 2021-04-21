package com.softserveinc.ita.home.home_project_blog.models;

//import io.swagger.annotations.ApiModelProperty;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 4, max = 200, message = "name must be between 4 and 200 characters.")
    private String name;

    @Size(min = 1, max = 200, message = "name must be between 1 and 200 characters.")
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 1, max = 200, message = "name must be between 1 and 200 characters.")
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Email(message = "Email should be valid.")
    //@Column(nullable = false)
    private String email;

    @NotNull
    @Size(min = 8, max = 200, message = "Password should be between 8 and 200 characters.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])")
    private String password;

    public static enum ROLE{
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
