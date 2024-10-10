package com.gasparscienza.prueba_sginnova.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import com.gasparscienza.prueba_sginnova.dtos.TaskDTO;
import com.gasparscienza.prueba_sginnova.model.Task;

@Mapper
public interface TaskMapper {
    
    TaskMapper mapper = Mappers.getMapper(TaskMapper.class);

    @Mapping(source="project", target="projectDTO")
    @Mapping(source="user", target="userDTO")
    TaskDTO taskToTaskDTO(Task task);

    @Mapping(source="projectDTO", target="project")
    @Mapping(source="userDTO", target="user")
    Task taskDtoToTask(TaskDTO taskDTO);
}
