package com.itacademy.blog.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A representation of User entity.
 */
@ApiModel(description = "A representation of User entity.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-22T13:30:27.722673500+03:00[Europe/Kiev]")
public class User  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;

  /**
   * This is the level of User access to various functions.
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

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoleEnum fromValue(String value) {
      for (RoleEnum b : RoleEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("role")
  private RoleEnum role;

  public User id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the User.
   * @return id
  */
  @ApiModelProperty(readOnly = true, value = "The ID of the User.")

  @Valid

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the User.
   * @return name
  */
  @ApiModelProperty(example = "John78", required = true, value = "The name of the User.")
  @NotNull

@Size(min=4) 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * The first name of the User.
   * @return firstName
  */
  @ApiModelProperty(example = "John", value = "The first name of the User.")

@Size(min=1) 
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * The last name of the User.
   * @return lastName
  */
  @ApiModelProperty(example = "Smith", value = "The last name of the User.")

@Size(min=1) 
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * The email of the User.
   * @return email
  */
  @ApiModelProperty(example = "john.smith@example.com", required = true, value = "The email of the User.")
  @NotNull

@javax.validation.constraints.Email
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * The password of the User. MUST contain a mix of upper and lower case letters, as well as digits.
   * @return password
  */
  @ApiModelProperty(example = "passworD321", value = "The password of the User. MUST contain a mix of upper and lower case letters, as well as digits.")

@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$") @Size(min=8) 
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * This is the level of User access to various functions.
   * @return role
  */
  @ApiModelProperty(value = "This is the level of User access to various functions.")


  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

