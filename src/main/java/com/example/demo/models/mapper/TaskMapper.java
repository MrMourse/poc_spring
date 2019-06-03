package com.example.demo.models.mapper;

import com.example.demo.models.task.TaskBO;
import com.example.demo.models.task.TaskDTO;
import com.example.demo.models.task.TaskEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper entre les différents objets concernant les tâches.
 */
@Mapper
public abstract class TaskMapper {

    public static final TaskMapper INSTANCE = Mappers.getMapper( TaskMapper.class );

    public abstract TaskBO entityToBo(TaskEntity task);

    public abstract TaskEntity boToEntity(TaskBO task);

    public abstract List<TaskBO> entitiesToBos(List<TaskEntity> tasks);

    public abstract TaskDTO boToDto(TaskBO task);

    public abstract TaskBO dtoToBo(TaskDTO task);

    public abstract List<TaskDTO> bosToDtos(List<TaskBO> tasks);
}

