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

  /**
   * Description of the task.
   */
  private Optional<@NotBlank(message = "description cannot be null or empty") String> description;
  
  /**
   * Completed status of the task.
   */
  private Optional<@NotNull(
      message = "completed cannot be null and it should be either 'true' or 'false'."
      ) Boolean> completed;

  /**
   * Default constructor.
   */
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
      final Optional<@NotBlank(message = "description cannot be null or empty") String> description,
      final Optional<@NotNull(
          message = "completed cannot be null and it should be either 'true' or 'false'."
          ) Boolean> completed) {
    super();
    this.description = description;
    this.completed = completed;
  }

  /**
   * Getter for optionalDescription.
   * @return optionalDescription returns the optionalDescription object of the task.
   */
  public Optional<String> getDescription() {
    return description;
  }

  /**
   * Setter for description.
   * @param description description of the task.
   */
  public void setDescription(final String description) {
    Optional<String> optionalDescription = Optional.of(description);
    this.description = optionalDescription;
  }

  /**
   * Getter for optionalCompleted.
   * @return optionalCompleted returns the optionalCompleted object with 
   * completed status of the task.
   */
  public Optional<Boolean> getCompleted() {
    return completed;
  }

  /**
   * Setter for completed.
   * @param completed completed status of the task.
   */
  public void setCompleted(final Boolean completed) {
    Optional<Boolean> optionalCompleted = Optional.of(completed);
    this.completed = optionalCompleted;
  }

}
