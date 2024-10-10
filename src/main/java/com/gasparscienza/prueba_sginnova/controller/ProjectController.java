package com.gasparscienza.prueba_sginnova.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
import com.gasparscienza.prueba_sginnova.model.Project;
import com.gasparscienza.prueba_sginnova.service.IProjectService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private IProjectService iProjectService;

    @PostMapping()
    public ResponseEntity<String> addProject(@Valid @RequestBody Project project) {
        try {
            iProjectService.addProject(project);
            return ResponseEntity.ok("Proyecto creado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el proyecto " + e);
        }
    }
    
    @GetMapping()
    public List<Project> getProjects() {
        return iProjectService.getProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> findProject(@PathVariable Long id) {
        Optional<Project> proyect = iProjectService.findProject(id);
        if(!proyect.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proyect);
    }

    @DeleteMapping("/{id}")
    public String delProject(@PathVariable Long id) {
        iProjectService.delProject(id);
        return "Proyecto Eliminado";
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
