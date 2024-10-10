package com.gasparscienza.prueba_sginnova.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasparscienza.prueba_sginnova.dtos.ProjectDTO;
import com.gasparscienza.prueba_sginnova.mapper.ProjectMapper;
import com.gasparscienza.prueba_sginnova.model.Project;
import com.gasparscienza.prueba_sginnova.repository.ProjectRepository;
import com.gasparscienza.prueba_sginnova.service.IProjectService;
import java.time.LocalDate;

@Service
public class ProjectService implements IProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public String addProject(Project project) {
        try {
            projectRepository.save(project);
            return "Proyecto creado correctamente";
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el proyecto " + e);
        }
        
    }

    @Override
    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = projects.stream().map(
            project -> ProjectMapper.mapper.projectToProjectDto(project)
        ).collect(Collectors.toList());
        
        return projectDTOs;
    }

    @Override
    public ProjectDTO findProjectDto(Long id) {
        Project project = projectRepository.findById(id).get();
        ProjectDTO projectDTO = ProjectMapper.mapper.projectToProjectDto(project);
        return projectDTO;
    }

    @Override
    public String delProject(Long id) {
        projectRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        try {
            projectRepository.deleteById(id);
            return "Proyecto eliminado correctamente";
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el proyecto " + e);
        }
        
    }



    @Override
    public void editProject(Long id, String name, String description, LocalDate startDate, LocalDate endDate) {
        Optional<Project> editProject = projectRepository.findById(id);
        editProject.ifPresent(project->{
            project.setName(name);
            project.setDescription(description);
            project.setEndDate(endDate);
            project.setStartDate(startDate);
            this.addProject(project);
        });
    }

    @Override
    public Optional<Project> findProject(Long id) {
        return projectRepository.findById(id);
    }
    
}
