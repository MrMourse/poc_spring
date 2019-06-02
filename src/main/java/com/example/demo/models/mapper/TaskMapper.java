package com.example.demo.models.mapper;

import com.example.demo.models.task.TaskBO;
import com.example.demo.models.task.TaskDTO;
import com.example.demo.models.task.TaskEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper( TaskMapper.class );

    TaskBO entityToBO(TaskEntity task);
    TaskEntity BOtoEntity(TaskBO task);
    List<TaskBO> entitiesToBOs(List<TaskEntity> tasks);
    TaskDTO BOToDTO(TaskBO task);
    TaskBO DTOToBO(TaskDTO task);
    List<TaskDTO> BOsToDTOs(List<TaskBO> tasks);
}

