package com.gasparscienza.prueba_sginnova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gasparscienza.prueba_sginnova.model.Proyect;

@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long>{
    
}
