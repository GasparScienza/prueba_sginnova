package com.gasparscienza.prueba_sginnova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gasparscienza.prueba_sginnova.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
