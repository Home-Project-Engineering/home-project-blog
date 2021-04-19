package com.softserveinc.ita.home.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Tag
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T11:28:34.593463600+03:00[Europe/Helsinki]")
public class Tag   {
  @JsonProperty("id")
  private long TagIid;

  @JsonProperty("name")
  private String name = null;

  public Tag id(long id) {
    this.TagIid = id;
    return this;
  }

  /**
   * Get TagIid
   * @return TagIid
  **/
  @ApiModelProperty(readOnly = true, value = "")
  
    @Valid
    public long getTagIid() {
    return TagIid;
  }

  public void setTagIid(long tagIid) {
    this.TagIid = tagIid;
  }

  public Tag name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Tag
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Tag")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
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
    Tag tag = (Tag) o;
    return Objects.equals(this.TagIid, tag.TagIid) &&
        Objects.equals(this.name, tag.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TagIid, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {\n");

    sb.append("    TagIid: ").append(toIndentedString(TagIid)).append("\n");
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
