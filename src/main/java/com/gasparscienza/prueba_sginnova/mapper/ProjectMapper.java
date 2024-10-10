package com.gasparscienza.prueba_sginnova.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gasparscienza.prueba_sginnova.dtos.ProjectDTO;
import com.gasparscienza.prueba_sginnova.model.Project;

@Mapper
public interface ProjectMapper {
    ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source="user",target = "userDTO")
    ProjectDTO projectToProjectDto(Project project);

    @Mapping(source="userDTO",target = "user")
    Project projectDtoToProject(ProjectDTO projectDto);
}
