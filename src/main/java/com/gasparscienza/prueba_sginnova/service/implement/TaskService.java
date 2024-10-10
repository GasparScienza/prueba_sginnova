package com.gasparscienza.prueba_sginnova.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.gasparscienza.prueba_sginnova.dtos.TaskDTO;
import com.gasparscienza.prueba_sginnova.mapper.TaskMapper;
import com.gasparscienza.prueba_sginnova.model.Project;
import com.gasparscienza.prueba_sginnova.model.Role;
import com.gasparscienza.prueba_sginnova.model.State;
import com.gasparscienza.prueba_sginnova.model.Task;
import com.gasparscienza.prueba_sginnova.model.User;
import com.gasparscienza.prueba_sginnova.repository.TaskRepository;
import com.gasparscienza.prueba_sginnova.service.IProjectService;
import com.gasparscienza.prueba_sginnova.service.ITaskService;
import com.gasparscienza.prueba_sginnova.service.IUserService;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepository taskRepository;
     @Autowired
    private IUserService iUserService;
    @Autowired
    private IProjectService iProjectService;

    /*
     * Editar estado de Tarea
     * Este metodo comprueba si la tarea ya se encuentra en el estado que se quiere actualzar
     * El ADMIN puede dar de alta cualquier tarea, un USER solo si la tarea pertenece al mismo
    */
    @Override
    public String editState(Long id, State state) {
        Optional<Task> taskEdit = this.findTask(id);
        //Obtengo el usuario logueado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userAuth = iUserService.findByUsername(authentication.getName());
        if (userAuth.isPresent() && taskEdit.isPresent()) {
            User user = userAuth.get();
            Task task = taskEdit.get();
            // Verificar si el usuario es ADMIN o si la tarea está asignada al usuario
            if (user.getRole() == Role.ADMIN || user.getId().equals(task.getUser().getId())) {
                if(task.getState() == state){//En caso de que el estado es el mismo que ya tiene la tarea
                    throw new RuntimeException("La tarea ya se encuentra en " + state);
                }
                try {
                    task.setState(state);
                    taskRepository.save(task);
                    return "Estado Actualizado";
                } catch (Exception e) {
                    throw new RuntimeException("Error al actualizar el estado");
                }
            } else {
                throw new RuntimeException("El usuario no tiene permisos para actualizar esta tarea.");
            }
        } else {
            // Manejar el caso en que el usuario o la tarea no existen
            if (!userAuth.isPresent()) {
                throw new RuntimeException("Usuario no encontrado.");
            } else {
                throw new RuntimeException("Tarea no encontrada.");
            }
        }
    }
    //Crear tarea
    /* 
     * Este metodo comprueba que el usuario a realizar el alta de una tarea
     * este asociado al proyecto de la misma.
     * Creando la tarea con el estado PENDIENTE
    */
    @Override
    public String addTask(Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userAuth = iUserService.findByUsername(authentication.getName());
        if (userAuth.isPresent()) {
            User user = userAuth.get();
            Project project = iProjectService.findProject(task.getProject().getId()).get();//Busco el proyecto asignado a la tarea
            if(project.getUser() == user){
                try {
                    task.setState(State.PENDIENTE);
                    taskRepository.save(task);
                    return "Tarea creada correctamente";
                } catch (Exception e) {
                    throw new RuntimeException("Error al crear la tarea " + e);
                }
                
            }else{
                throw new RuntimeException("No estas asociado al proyecto para realizar el alta de una tarea");
            }
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public List<TaskDTO> findTasksByProjectId(Long id) {
       List<Task> tasks = taskRepository.findByProject_id(id);
       List<TaskDTO> taskDTOs = tasks.stream().map(
            task -> TaskMapper.mapper.taskToTaskDTO(task)
        ).collect(Collectors.toList());
        return taskDTOs;
    }
    @Override
    public Optional<Task> findTask(Long id) {
        return taskRepository.findById(id);
    }


    /* Demas metodos del CRUD de tareas
    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
    @Override
    public void delTask(Long id) {
        taskRepository.deleteById(id);
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
        */
}
