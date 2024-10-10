package com.gasparscienza.prueba_sginnova.service;

import com.gasparscienza.prueba_sginnova.model.State;
import com.gasparscienza.prueba_sginnova.model.Task;
import java.util.List;
import java.util.Optional;


public interface ITaskService {
    public void addTask(Task task);
    public List<Task> getTasks();
    public void delTask(Long id);
    public Optional<Task> findTask(Long id);
    public void editTask(Long id, String name, String description, State state);
    public void editState(Long id, State state);
    public List<Task> findTasksByProjectId(Long id);
}
