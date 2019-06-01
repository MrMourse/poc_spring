package com.example.demo.services.task;

import com.example.demo.exceptions.BusinessResourceException;
import com.example.demo.models.mapper.TaskMapper;
import com.example.demo.models.task.TaskBO;
import com.example.demo.models.task.TaskEntity;
import com.example.demo.repositories.TaskRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskBO> getAllTasks() throws BusinessResourceException {
        List<TaskEntity> taskEntitiesFound
                = IteratorUtils.toList(taskRepository.findAll().iterator());
        return TaskMapper.INSTANCE.entitiesToBOs(taskEntitiesFound);
    }

    @Override
    public TaskBO getTaskById(Long id) {
        try {
            Optional<TaskEntity> taskFound = taskRepository.findById(id);
            if (taskFound.isPresent()){
                return TaskMapper.INSTANCE.entityToBO(taskFound.get());
            }
            else{
                throw new BusinessResourceException("UserNotFound", "Erreur utilisation non trouvé avec l'id :" +id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            throw new BusinessResourceException("Delete User Error", "Erreur de suppression de l'utilisateur avec l'identifiant: "+id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TaskBO getTaskByTitle(String title) throws BusinessResourceException {
        try {
            Optional<TaskEntity> taskFound = taskRepository.findByTitle(title);
            if (taskFound.isPresent()){
                return TaskMapper.INSTANCE.entityToBO(taskFound.get());
            }
            else{
                throw new BusinessResourceException("TaskNotFound", "Erreur tâche non trouvé avec le titre :" + title, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            throw new BusinessResourceException("Delete User Error", "Erreur de suppression de l'utilisateur avec le titre: "+ title, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TaskBO saveOrUpdateTask(TaskBO task) {
        try{
            //Check if we make a modification
            TaskEntity taskToSave = TaskMapper.INSTANCE.BOtoEntity(task);
            taskRepository.save(taskToSave);
            return task;
        }catch(Exception ex){
            throw new BusinessResourceException("Create Or Update User Error", "Erreur de création ou de mise à jour de l'utilisateur: "+task.getTitle(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteTask(Long id) throws BusinessResourceException {
        try{
            taskRepository.deleteById(id);
        }catch(Exception ex){
            throw new BusinessResourceException("Delete User Error", "Erreur de suppression de l'utilisateur avec l'identifiant: "+id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
