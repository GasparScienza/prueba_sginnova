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
import com.gasparscienza.prueba_sginnova.model.Proyect;
import com.gasparscienza.prueba_sginnova.service.IProyectService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/proyect")
public class ProyectController {
    
    @Autowired
    private IProyectService iProyectService;

    @PostMapping("/add")
    public ResponseEntity<String> addProyect(@Valid @RequestBody Proyect proyect) {
        try {
            iProyectService.addProyect(proyect);
            return ResponseEntity.ok("Proyecto creado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el proyecto " + e);
        }
    }

    @GetMapping("/get")
    public List<Proyect> getProyects() {
        return iProyectService.getProyects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Proyect>> findProyect(@PathVariable Long id) {
        Optional<Proyect> proyect = iProyectService.findProyect(id);
        if(!proyect.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proyect);
    }

    @DeleteMapping("/del/{id}")
    public String delProyect(@PathVariable Long id) {
        iProyectService.delProyect(id);
        return "Proyecto Eliminado";
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editProyect(@PathVariable Long id, @Valid @RequestBody Proyect proyect) {
        try {
            iProyectService.editProyect(id, proyect.getName(), proyect.getDescription(), proyect.getStartDate(), proyect.getEndDate());
            return ResponseEntity.ok("Proyecto editado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar el proyecto" + e);
        }
    }
}
