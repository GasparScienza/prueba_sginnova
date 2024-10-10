package com.gasparscienza.prueba_sginnova.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasparscienza.prueba_sginnova.model.Project;
import com.gasparscienza.prueba_sginnova.repository.ProjectRepository;
import com.gasparscienza.prueba_sginnova.service.IProjectService;
import java.time.LocalDate;

@Service
public class ProjectService implements IProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void delProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Optional<Project> findProject(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void editProject(Long id, String name, String description, LocalDate startDate, LocalDate endDate) {
        Optional<Project> editProject = this.findProject(id);
        editProject.ifPresent(project->{
            project.setName(name);
            project.setDescription(description);
            project.setEndDate(endDate);
            project.setStartDate(startDate);
            this.addProject(project);
        });
    }
    
}
