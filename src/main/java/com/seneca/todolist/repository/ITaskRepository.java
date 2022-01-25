package com.seneca.todolist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seneca.todolist.entity.TaskEntity;
import com.seneca.todolist.entity.UserEntity;

public interface ITaskRepository extends JpaRepository<TaskEntity, String> {

  Optional<List<TaskEntity>> findAllByUser(UserEntity user);

  Optional<TaskEntity> findByUserAndTaskId(UserEntity user, String id);

  @Query(value = "select * from tasks t where t.owner_id=?1 and completed=?2", nativeQuery = true)
  Optional<List<TaskEntity>> findAllByUserAndCompletedStatus(UserEntity user,
      Boolean status);

  Page<TaskEntity> findAllByUser(UserEntity user, Pageable pageable);

  @Query(value = "select * from tasks t where t.owner_id=?1 order by created_at limit ?2 offset ?3 ", nativeQuery = true)
  Optional<List<TaskEntity>> findAllByPagination(UserEntity user, Integer limit,
      Integer skip);

  void deleteByUserAndTaskId(UserEntity user, String taskId);

}
