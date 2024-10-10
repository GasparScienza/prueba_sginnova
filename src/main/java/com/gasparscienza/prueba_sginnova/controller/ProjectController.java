package com.gasparscienza.prueba_sginnova.controller;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.gasparscienza.prueba_sginnova.dtos.ProjectDTO;
import com.gasparscienza.prueba_sginnova.model.Project;
import com.gasparscienza.prueba_sginnova.service.IProjectService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private IProjectService iProjectService;

    @PostMapping()
    public ResponseEntity<Map<String, Object>> addProject(@Valid @RequestBody Project project) {
        Map<String, Object> response = new HashMap<>();
        try {
            iProjectService.addProject(project);
            response.put("success", true);
            response.put("message", "Proyecto creado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al crear el proyecto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response); 
        }
    }
    
    @GetMapping()
    public ResponseEntity<?> getProjects() {
        List<ProjectDTO> projects = iProjectService.getProjects();
        if (projects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("No se encontraron proyectos.");
        }
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProject(@PathVariable Long id) {
        ProjectDTO projectDTO = iProjectService.findProjectDto(id);
        if (projectDTO == null) {
            // Si no se encuentra el proyecto, devolver un mensaje con estado 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Proyecto con ID " + id + " no encontrado.");
        }
        return ResponseEntity.ok(projectDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delProject(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            iProjectService.delProject(id);
            response.put("success", true);
            response.put("message", "Proyecto eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al eliminar el proyecto: " + e.getMessage());
            return ResponseEntity.badRequest().body(response); 
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editProject(@PathVariable Long id, @Valid @RequestBody Project project) {
        try {
            iProjectService.editProject(id, project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate());
            return ResponseEntity.ok("Proyecto editado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar el proyecto" + e);
        }
    }
}
