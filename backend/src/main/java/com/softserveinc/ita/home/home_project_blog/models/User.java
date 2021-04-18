package com.softserveinc.ita.home.home_project_blog.models;

//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    //@ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 4, max = 200, message = "name must be between 4 and 200 characters.")
    private String name;

    @Size(min = 1, max = 200, message = "name must be between 1 and 200 characters.")
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 1, max = 200, message = "name must be between 1 and 200 characters.")
    @Column(name = "lastname")
    private String lastName;

    @Email(message = "Email should be valid.")
    @Column(nullable = false)
    private String email;

    @Size(min = 8, max = 200, message = "Password should be between 8 and 200 characters.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])")
    private String password;

    //    private static final enum ROLE{
//        guest, user, moderator, admin, expert
//    }
    private String role;


   /* @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
//        if (this.population != other.population) {
//            return false;
//        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
//        sb.append(", population=").append(population);
        sb.append('}');
        return sb.toString();
    }*/
   /* @Override
    public String toString() {
        return "[\"id\":\"" + id + "\",\"name\":\"" + name + "\"]";
    }*/

}

 /*

    public User id(BigDecimal id) {
        this.id = id;
        return this;
    }

    @ApiModelProperty(
            readOnly = true,
            value = ""
    )
    @Valid
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty(
            example = "John78",
            required = true,
            value = "User supplied username"
    )
    @NotNull
    @Size(
            min = 4
    )
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @ApiModelProperty(
            example = "John",
            value = "User first name"
    )
    @Size(
            min = 1
    )
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @ApiModelProperty(
            example = "Smith",
            value = "User last name"
    )
    @Size(
            min = 1
    )
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    @ApiModelProperty(
            example = "john.smith@example.com",
            required = true,
            value = "User email address"
    )
    @NotNull
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    @ApiModelProperty(
            example = "passworD321",
            value = "User password, MUST contain a mix of upper and lower case letters, as well as digits"
    )
    @Pattern(
            regexp = "/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])/"
    )
    @Size(
            min = 8
    )
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User role(User.RoleEnum role) {
        this.role = role;
        return this;
    }

    @ApiModelProperty("This is the level of user access to various functions")
    public User.RoleEnum getRole() {
        return this.role;
    }

    public void setRole(User.RoleEnum role) {
        this.role = role;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            User user = (User) o;
            return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name) && Objects.equals(this.firstName, user.firstName) && Objects.equals(this.lastName, user.lastName) && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password) && Objects.equals(this.role, user.role);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.name, this.firstName, this.lastName, this.email, this.password, this.role});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(this.toIndentedString(this.name)).append("\n");
        sb.append("    firstName: ").append(this.toIndentedString(this.firstName)).append("\n");
        sb.append("    lastName: ").append(this.toIndentedString(this.lastName)).append("\n");
        sb.append("    email: ").append(this.toIndentedString(this.email)).append("\n");
        sb.append("    password: ").append(this.toIndentedString(this.password)).append("\n");
        sb.append("    role: ").append(this.toIndentedString(this.role)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }

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
