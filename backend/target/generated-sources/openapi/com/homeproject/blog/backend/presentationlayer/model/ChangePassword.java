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
 * Representation of Change Password entity.
 */
@ApiModel(description = "Representation of Change Password entity.")

public class ChangePassword   {
  @JsonProperty("oldPassword")
  private String oldPassword;

  @JsonProperty("newPassword")
  private String newPassword;

  public ChangePassword oldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
    return this;
  }

  /**
   * Existing password.
   * @return oldPassword
  */
  @ApiModelProperty(required = true, value = "Existing password.")
  @NotNull


  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public ChangePassword newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  /**
   * The password of the User. MUST contain a mix of upper and lower case letters, as well as digits.
   * @return newPassword
  */
  @ApiModelProperty(example = "passworD321", required = true, value = "The password of the User. MUST contain a mix of upper and lower case letters, as well as digits.")
  @NotNull

@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$") @Size(min=8) 
  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangePassword changePassword = (ChangePassword) o;
    return Objects.equals(this.oldPassword, changePassword.oldPassword) &&
        Objects.equals(this.newPassword, changePassword.newPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(oldPassword, newPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangePassword {\n");
    
    sb.append("    oldPassword: ").append(toIndentedString(oldPassword)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
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

