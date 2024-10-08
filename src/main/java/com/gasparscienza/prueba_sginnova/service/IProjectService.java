package com.gasparscienza.prueba_sginnova.service;

import com.gasparscienza.prueba_sginnova.dtos.ProjectDTO;
import com.gasparscienza.prueba_sginnova.model.Project;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface IProjectService {
    public String addProject(Project project);
    public List<ProjectDTO> getProjects();
    public String delProject(Long id);
    public ProjectDTO findProjectDto(Long id);
    public Optional<Project> findProject(Long id);
    public void editProject(Long id, String name, String description, LocalDate startDate, LocalDate endDate);
}
