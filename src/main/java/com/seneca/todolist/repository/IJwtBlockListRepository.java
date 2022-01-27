package com.seneca.todolist.repository;

import com.seneca.todolist.entity.JwtBlockListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJwtBlockListRepository extends JpaRepository<JwtBlockListEntity, String> {

  JwtBlockListEntity findByTokenSpecificIdAndEmail(String jwtToken, String email);

}
