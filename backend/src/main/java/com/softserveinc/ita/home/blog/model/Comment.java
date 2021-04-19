package com.softserveinc.ita.home.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Comment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T11:28:34.593463600+03:00[Europe/Helsinki]")
public class Comment   {
  @JsonProperty("id")
  private long CommentId;

  @JsonProperty("user")
  private User user = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("postId")
  private long postId;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  public Comment id(long id) {
    this.CommentId = id;
    return this;
  }

  /**
   * Comment's Id
   * @return CommentId
  **/
  @ApiModelProperty(readOnly = true, value = "Comment's Id")
  
    @Valid
    public long getCommentId() {
    return CommentId;
  }

  public void setCommentId(long commentId) {
    this.CommentId = commentId;
  }

  public Comment user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Comment text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Comment's text
   * @return text
  **/
  @ApiModelProperty(required = true, value = "Comment's text")
      @NotNull

    public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Comment createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * Creation date
   * @return createdOn
  **/
  @ApiModelProperty(example = "2017-07-21T17:32:28Z", readOnly = true, value = "Creation date")
  
    @Valid
    public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public Comment postId(long postId) {
    this.postId = postId;
    return this;
  }

  /**
   * The CommentId of the Post that exists in the blog.
   * @return postId
  **/
  @ApiModelProperty(required = true, value = "The CommentId of the Post that exists in the blog.")
      @NotNull

    @Valid
    public long getPostId() {
    return postId;
  }

  public void setPostId(long postId) {
    this.postId = postId;
  }

  public Comment updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * Date of update
   * @return updatedOn
  **/
  @ApiModelProperty(example = "2017-07-21T17:32:28Z", readOnly = true, value = "Date of update")
  
    @Valid
    public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(this.CommentId, comment.CommentId) &&
        Objects.equals(this.user, comment.user) &&
        Objects.equals(this.text, comment.text) &&
        Objects.equals(this.createdOn, comment.createdOn) &&
        Objects.equals(this.postId, comment.postId) &&
        Objects.equals(this.updatedOn, comment.updatedOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(CommentId, user, text, createdOn, postId, updatedOn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");

    sb.append("    CommentId: ").append(toIndentedString(CommentId)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    postId: ").append(toIndentedString(postId)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
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
