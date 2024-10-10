package com.gasparscienza.prueba_sginnova.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gasparscienza.prueba_sginnova.dtos.TaskStateDTO;
import com.gasparscienza.prueba_sginnova.model.State;
import com.gasparscienza.prueba_sginnova.model.Task;
import com.gasparscienza.prueba_sginnova.service.ITaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskService iTaskService;

 
    //En este metodo Actualizamos el estado de una tarea
    @PatchMapping("/{id}")
    public ResponseEntity<String> editState(@PathVariable Long id, @Valid @RequestBody TaskStateDTO taskStateDTO){
        try {
            State state = taskStateDTO.getState();
            iTaskService.editState(id, state);
            return ResponseEntity.ok("Estado de la tarea fue actualizado a " + state);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el estado" + e);
        }
    }
    //Metodo para crear una tarea
    @PostMapping()
    public ResponseEntity<String> addTask(@Valid @RequestBody Task task) {
        try {
            iTaskService.addTask(task);
            return ResponseEntity.ok("Tarea creada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la tarea " + e);
        }
    }
    

    @GetMapping()
    public List<Task> getTasks(@RequestParam(value = "project_id") Long id) {
        return iTaskService.findTasksByProjectId(id);
    }

    /*
     * Borrar demas metodos
     * 
     * 
     */


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> findTask(@PathVariable Long id) {
        Optional<Task> task = iTaskService.findTask(id);
        if(!task.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public String delTask(@PathVariable Long id) {
        iTaskService.delTask(id);
        return "Tarea Eliminada";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        try {
            iTaskService.editTask(id, task.getName(), task.getDescription(), task.getState());
            return ResponseEntity.ok("Tarea editada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar la tarea" + e);
        }
    }
}
