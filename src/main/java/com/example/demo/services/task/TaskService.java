package com.example.demo.services.task;

import com.example.demo.exceptions.BusinessResourceException;
import com.example.demo.models.task.TaskBO;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface TaskService {

    List<TaskBO> getAllTasks() throws BusinessResourceException;

    TaskBO getTaskById(Long id) throws BusinessResourceException;

    TaskBO getTaskByTitle(String title) throws BusinessResourceException;

    TaskBO saveOrUpdateTask(TaskBO task) throws BusinessResourceException;

    void deleteTask(Long id) throws BusinessResourceException;

}
