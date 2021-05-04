package com.softserveinc.ita.homeprojectblog.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is the level of User access to various functions.
 */
@ApiModel(description = "This is the level of User access to various functions.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-04T04:46:41.921649900+03:00[Europe/Helsinki]")
public class RoleEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * The name of the RoleDto.
   */
  public enum NameEnum {
    BLOGGER("blogger"),
    
    MODERATOR("moderator"),
    
    ADMIN("admin");

    private String value;

    NameEnum(String value) {
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
    public static NameEnum fromValue(String value) {
      for (NameEnum b : NameEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("name")
  private NameEnum name = NameEnum.BLOGGER;

  public RoleEntity name(NameEnum name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the RoleDto.
   * @return name
  */
  @ApiModelProperty(value = "The name of the RoleDto.")


  public NameEnum getName() {
    return name;
  }

  public void setName(NameEnum name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleEntity roleEntity = (RoleEntity) o;
    return Objects.equals(this.name, roleEntity.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleDto {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

