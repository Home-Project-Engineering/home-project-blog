package com.homeproject.blog.backend.presentationlayer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A representation of Author entity.
 */
@ApiModel(description = "A representation of Author entity.")

public class Author   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  public Author name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The Author nickname.
   * @return name
  */
  @ApiModelProperty(value = "The Author nickname.")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Author firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * The first name of the Author.
   * @return firstName
  */
  @ApiModelProperty(value = "The first name of the Author.")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Author lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * The last name of the Author.
   * @return lastName
  */
  @ApiModelProperty(value = "The last name of the Author.")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return Objects.equals(this.name, author.name) &&
        Objects.equals(this.firstName, author.firstName) &&
        Objects.equals(this.lastName, author.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, firstName, lastName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Author {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

