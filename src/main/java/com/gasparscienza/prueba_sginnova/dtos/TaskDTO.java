package com.gasparscienza.prueba_sginnova.dtos;

import com.gasparscienza.prueba_sginnova.model.State;
import lombok.Data;

@Data
public class TaskDTO {
    
    private Long id;
    private String name;
    private String description;
    private State state;
    private ProjectDTO projectDTO;
    private UserDTO userDTO;
}
