package com.example.demo.services.task;

import com.example.demo.models.mapper.TaskMapper;
import com.example.demo.models.task.TaskBO;
import com.example.demo.models.task.TaskEntity;
import com.example.demo.repositories.TaskRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    /**
     * Initialisation du task repository.
     * @param taskRepository, service de gestion des taches pour la couche de la base de données.
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

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
        TaskEntity taskToSave = TaskMapper.INSTANCE.boToEntity(task);
        taskToSave = taskRepository.save(taskToSave);
        return TaskMapper.INSTANCE.entityToBo(taskToSave);
    }

    @Override
    public void deleteTask(Long id){
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Didn't find a task with id " + id);
        }
    }

}
