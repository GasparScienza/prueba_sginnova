package com.gasparscienza.prueba_sginnova.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gasparscienza.prueba_sginnova.dtos.UserDTO;
import com.gasparscienza.prueba_sginnova.model.User;

@Mapper
public interface UserMapper {
    
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDto);

}
