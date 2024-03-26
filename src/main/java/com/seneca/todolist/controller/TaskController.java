package com.seneca.todolist.controller;

import com.seneca.todolist.exception.InvalidRequestException;
import com.seneca.todolist.exception.ResourceNotFoundException;
import com.seneca.todolist.model.TaskInfoDto;
import com.seneca.todolist.model.TaskInfoUpdateDto;
import com.seneca.todolist.model.TaskResponseDto;
import com.seneca.todolist.service.TaskService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller that handles the requests on the task related apis.
 *
 * @author hemanth.nagireddy
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {

  /**
   * A constant for header name - Authorization.
   */
  private static final String AUTHORIZATION = "Authorization";

  /**
   * TaskService interface injection into this class.
   */
  @Autowired
  private TaskService itaskService;

  /**
   * This is the method to add any task.
   *
   * @param request     This is the http servlet request.
   * @param taskInfoDto This is the task information dto which takes the task
   *                    information from the user.
   * @return This method returns a task response dto which is a combination of
   *         status of the operation along with details of the info added.
   *
   * @throws InvalidRequestException thrown in case of an invalid request.
   */
  @PostMapping("")
  public ResponseEntity<Object> addTask(final HttpServletRequest request,
      final @RequestBody TaskInfoDto taskInfoDto) throws InvalidRequestException {

    if (!StringUtils.hasText(taskInfoDto.getDescription().trim())) {
      throw new InvalidRequestException("Task description cannot be null.");
    }
    final TaskResponseDto taskResponseDto = itaskService.addTask(taskInfoDto,
        request.getHeader("Authorization"));
    return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
  }

  /**
   * This method fetches all the tasks within the database specific to the
   * logged in user.
   *
   * @param request This is the HttpServletRequest.
   * @return Returns all the tasks of the logged in user.
   */
  @GetMapping("")
  public ResponseEntity<Object> getAllTasks(final HttpServletRequest request) {
    return new ResponseEntity<>(
        itaskService.getAllTasks(request.getHeader(AUTHORIZATION)),
        HttpStatus.OK);
  }

  /**
   * This method is to fetch the task by passing the taskId.
   *
   * @param taskId  The task id determines the id associated with the id.
   * @param request This is the HttpServletRequest.
   * @return This returns the task info w.r.t the id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getTaskById(final @PathVariable("id") String taskId,
      final HttpServletRequest request) {
    if (!StringUtils.hasText(taskId.trim())) {
      throw new ResourceNotFoundException(
          "Task with the given id does not exist");
    }
    return new ResponseEntity<>(
        itaskService.getTaskById(taskId, request.getHeader("Authorization")),
        HttpStatus.OK);
  }

  /**
   * This method is to fetch the tasks w.r.t. their completed status
   *
   * @param status  This is to query the tasks w.r.t. completed status.
   * @param request This is the HttpServletRequest.
   * @return tasks Returns the tasks along with count.
   */
  @GetMapping(value = "", params = "completed")
  public ResponseEntity<Object> getTasksByCompletedStatus(
      final @RequestParam("completed") boolean status, final HttpServletRequest request) {
    return new ResponseEntity<>(itaskService.getAllTasksByCompletedStatus(
        status, request.getHeader("Authorization")), HttpStatus.OK);
  }

  /**
   * This method is to update the task by id.
   *
   * @param request           This is the HttpServletRequest.
   * @param taskId            This is the task id corresponding to the task that
   *                          needs to be updated.
   * @param taskInfoUpdateDto This is the model class for updating task info.
   * @return taskResponseDto  Returns the updated task details.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateTaskById(final HttpServletRequest request,
      final @PathVariable("id") String taskId,
      final @Valid @RequestBody TaskInfoUpdateDto taskInfoUpdateDto) {

    if (!StringUtils.hasText(taskId.trim())) {
      throw new ResourceNotFoundException(
          "Task with the given id does not exist");
    }
    return new ResponseEntity<>(itaskService.updateTaskById(taskInfoUpdateDto,
        taskId, request.getHeader("Authorization")), HttpStatus.OK);
  }

  /**
   * This method fetches all the tasks between the pagination interval w.r.t
   * created date.
   *
   * @param request This is the HttpServletRequest.
   * @param limit   This is the number of records to be fetched.
   * @param skip    This is the number of records to be skipped before fetching
   *                the required number of records i.e. limit.
   * @return Returns all the tasks of the logged in user within the pagination
   *         interval.
   */
  @GetMapping(value = "", params = { "limit", "skip" })
  public ResponseEntity<Object> getPaginatedTasks(
      final @RequestParam("limit") Integer limit, final @RequestParam("skip") Integer skip,
      final HttpServletRequest request) {
    return new ResponseEntity<>(itaskService.getPaginatedTasks(
        request.getHeader("Authorization"), limit, skip), HttpStatus.OK);
  }

  /**
   * This method deletes the task of the logged in user by id.
   *
   * @param request This is the HttpServletRequest.
   * @param taskId This is the id representing task.
   * @return status Status of the delete action.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTaskById(final HttpServletRequest request,
      final @PathVariable("id") String taskId) {
    if (!StringUtils.hasText(taskId.trim())) {
      throw new ResourceNotFoundException(
          "Task with the given id does not exist");
    }
    final Boolean status = itaskService
        .deleteTaskById(request.getHeader("Authorization"), taskId);
    return status ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
