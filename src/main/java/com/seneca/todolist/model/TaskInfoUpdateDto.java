package com.seneca.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This is the dto used for taskInfoUpdate purposes.
 *
 * @author hemanth.nagireddy
 *
 */
@JsonInclude(Include.NON_NULL)
public class TaskInfoUpdateDto implements Serializable {

  private Optional<@NotBlank(message = "description cannot be null or empty") String> description;
  private Optional<@NotNull(
      message = "completed cannot be null and it should be either 'true' or 'false'."
      ) Boolean> completed;

  public TaskInfoUpdateDto() {
    super();
  }

  
  /**
   * This is the constructor for this class.
   *
   * @param description This is the description associated with the update request.
   * @param completed This is the completed status of the task.
   */
  public TaskInfoUpdateDto(
      Optional<@NotBlank(message = "description cannot be null or empty") String> description,
      Optional<@NotNull(
          message = "completed cannot be null and it should be either 'true' or 'false'."
          ) Boolean> completed) {
    super();
    this.description = description;
    this.completed = completed;
  }

  public Optional<String> getDescription() {
    return description;
  }

  public void setDescription(String description) {
    Optional<String> optionalDescription = Optional.of(description);
    this.description = optionalDescription;
  }

  public Optional<Boolean> getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    Optional<Boolean> optionalCompleted = Optional.of(completed);
    this.completed = optionalCompleted;
  }

}
