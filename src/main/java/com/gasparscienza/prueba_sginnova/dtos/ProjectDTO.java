package com.gasparscienza.prueba_sginnova.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserDTO userDTO;
}
