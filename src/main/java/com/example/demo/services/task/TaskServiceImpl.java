package com.example.demo.services.task;

import com.example.demo.models.mapper.TaskMapper;
import com.example.demo.models.task.TaskBO;
import com.example.demo.models.task.TaskEntity;
import com.example.demo.repositories.TaskRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskBO> getAllTasks() {
        List<TaskEntity> taskEntitiesFound
                = IteratorUtils.toList(taskRepository.findAll().iterator());
        return TaskMapper.INSTANCE.entitiesToBos(taskEntitiesFound);
    }

    @Override
    public TaskBO getTaskById(Long id) {
        Optional<TaskEntity> taskFound = taskRepository.findById(id);
        if (!taskFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a task with id" + id);
        }
        return TaskMapper.INSTANCE.entityToBo(taskFound.get());
    }

    @Override
    public TaskBO getTaskByTitle(String title){
        Optional<TaskEntity> taskFound = taskRepository.findByTitle(title);
        if (!taskFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a task with title " + title);
        }
        return TaskMapper.INSTANCE.entityToBo(taskFound.get());
    }

    @Override
    public TaskBO saveOrUpdateTask(TaskBO task) {
        //Check if we make a modification
        TaskEntity taskToSave = TaskMapper.INSTANCE.boToEntity(task);
        taskToSave = taskRepository.save(taskToSave);
        return TaskMapper.INSTANCE.entityToBo(taskToSave);
    }

    @Override
    public void deleteTask(Long id){
            taskRepository.deleteById(id);
    }

}
