package com.gasparscienza.prueba_sginnova.service;

import com.gasparscienza.prueba_sginnova.model.Proyect;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface IProyectService {
    public void addProyect(Proyect proyect);
    public List<Proyect> getProyects();
    public void delProyect(Long id);
    public Optional<Proyect> findProyect(Long id);
    public void editProyect(Long id, String name, String description, LocalDate startDate, LocalDate endDate);
}
