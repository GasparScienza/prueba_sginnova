package com.gasparscienza.prueba_sginnova.dtos;

import com.gasparscienza.prueba_sginnova.model.Role;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
