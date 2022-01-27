package com.seneca.todolist.service;

import com.seneca.todolist.model.TaskInfoDto;
import com.seneca.todolist.model.TaskInfoUpdateDto;
import com.seneca.todolist.model.TaskListResponseDto;
import com.seneca.todolist.model.TaskResponseDto;

/**
 * Interface with all the task related functionalities.
 *
 * @author hemanth.nagireddy
 *
 */
public interface TaskService {

  /**
   * This method is to add new task to database.
   *
   * @param taskInfoDto Takes the task info given by user.
   * @param token       This is the jwt token to identify the user logged in and
   *                    to add the task in his/her name.
   * @return TaskResponseDto This is the updated task info.
   */
  TaskResponseDto addTask(TaskInfoDto taskInfoDto, String token);

  /**
   * This method is to retrieve all the tasks assigned to the logged in user.
   *
   * @param token This is the jwt token to identify the logged in user.
   * @return TaskListResponseDto This contains all the tasks pertaining to the
   *         user.
   */
  TaskListResponseDto getAllTasks(String token);

  /**
   * This method is to retrieve the task with the given task id.
   *
   * @param token This is the jwt token to identify the logged in user.
   * @param taskId    This is the id associated with the task.
   * @return TaskResponseDto This contains task information requested by task
   *         id.
   */
  TaskResponseDto getTaskById(String taskId, String token);

  /**
   * This method is to retrieve all the tasks that are completed by the user.
   *
   * @param status Task status completed/not.
   * @param token  This is the jwt token to identify the logged in user.
   * @return TaskListResponseDto This contains all the completed tasks.
   */
  TaskListResponseDto getAllTasksByCompletedStatus(Boolean status,
      String token);

  /**
   * This method is to update the task w.r.t to the given id.
   *
   * @param taskInfoUpdateDto This is task information updated by the user.
   * @param taskId                This is the task id of a task that user is willing
   *                          to update.
   * @param token             This is the jwt token to identify the logged in
   *                          user.
   * @return TaskListResponseDto This contains all the completed tasks.
   */
  TaskResponseDto updateTaskById(TaskInfoUpdateDto taskInfoUpdateDto, String taskId,
      String token);

  /**
   * This method is to retrieve the tasks with given limit and offset(skip).
   *
   * @param token This is the authorization token.
   * @param limit This is the number of records to be retrieved.
   * @param skip  This is the offset or the number of items to be skipped before
   *              fetching the required number of records.
   * @return TaskListResponseDto This is an object with the count and tasks
   *         within the paginated result.
   */
  TaskListResponseDto getPaginatedTasks(String token, Integer limit,
      Integer skip);

  /**
   * This method is to delete the task by taskId and email from the token.
   *
   * @param taskId    This is the task id of a task that user is willing to update.
   * @param token This is the jwt token to identify the logged in user.
   * @return true/false This is the status whether deletion is failed or
   *         success.
   */
  boolean deleteTaskById(String token, String taskId);

}
