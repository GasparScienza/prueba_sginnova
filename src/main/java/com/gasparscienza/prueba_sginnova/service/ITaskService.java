package com.gasparscienza.prueba_sginnova.service;

import com.gasparscienza.prueba_sginnova.dtos.TaskDTO;
import com.gasparscienza.prueba_sginnova.model.State;
import com.gasparscienza.prueba_sginnova.model.Task;
import java.util.List;
import java.util.Optional;


public interface ITaskService {
    public String addTask(Task task);
    public List<TaskDTO> findTasksByProjectId(Long id);
    public String editState(Long id, State state);
    public Optional<Task> findTask(Long id);

    /*Demas metodos del CRUD de tareas
    public List<Task> getTasks();
    public void delTask(Long id);
    public void editTask(Long id, String name, String description, State state);
     */
}
