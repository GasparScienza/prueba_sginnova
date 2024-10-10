package com.gasparscienza.prueba_sginnova.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gasparscienza.prueba_sginnova.dtos.TaskDTO;
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
    public ResponseEntity<Map<String, Object>> editState(@PathVariable Long id, @Valid @RequestBody TaskStateDTO taskStateDTO){
        Map<String, Object> response = new HashMap<>();
        try {
            State state = taskStateDTO.getState();
            iTaskService.editState(id, state);

            response.put("success", true);
            response.put("message", "Estado de la tarea fue actualizado a " + state);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al actualizar el estado: " + e.getMessage());
            return ResponseEntity.badRequest().body(response); 
        }
    }
    //Metodo para crear una tarea
    @PostMapping()
    public ResponseEntity<Map<String, Object>> addTask(@Valid @RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();
        try {
            iTaskService.addTask(task);
            response.put("success", true);
            response.put("message", "La tarea fue creada correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al crear la tarea: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Metodo para mostrar las tareas de un proyecto
    @GetMapping()
    public List<TaskDTO> getTasks(@RequestParam(value = "project_id") Long id) {
        return iTaskService.findTasksByProjectId(id);
    }
}
