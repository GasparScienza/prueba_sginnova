package com.gasparscienza.prueba_sginnova.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gasparscienza.prueba_sginnova.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByProject_id(Long id);
}
