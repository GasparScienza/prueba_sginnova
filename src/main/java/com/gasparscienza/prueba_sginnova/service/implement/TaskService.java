package com.gasparscienza.prueba_sginnova.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasparscienza.prueba_sginnova.model.State;
import com.gasparscienza.prueba_sginnova.model.Task;
import com.gasparscienza.prueba_sginnova.repository.TaskRepository;
import com.gasparscienza.prueba_sginnova.service.ITaskService;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepository taskRepository;


    public List<Task> findTasksByProjectId(Long id) {
        return taskRepository.findByProject_id(id);
    }

    @Override
    public void addTask(Task task) {
        task.setState(State.PENDIENTE);
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void delTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Optional<Task> findTask(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void editTask(Long id, String name, String description, State state) {
        Optional<Task> taskEdit = this.findTask(id);
        taskEdit.ifPresent(task -> {
            task.setDescription(description);
            task.setName(name);
            task.setState(state);
            taskRepository.save(task);
        });
    }

    @Override
    public void editState(Long id, State state) {
        Optional<Task> taskEdit = this.findTask(id);
        taskEdit.ifPresent(task->{
            task.setState(state);
            taskRepository.save(task);
        });
    }
    
}
