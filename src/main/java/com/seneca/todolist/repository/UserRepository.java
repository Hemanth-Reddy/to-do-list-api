package com.seneca.todolist.repository;

import com.seneca.todolist.entity.UserEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the user repository extending the jpa repository.
 *
 * @author hemanth.nagireddy
 *
 */
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, String> {

  UserEntity findByEmailAndPassword(String email, String password);

  UserEntity findByEmail(String username);

  int deleteByEmail(String emailFromToken);

}
