package com.example.demo.services.task;

import com.example.demo.models.task.TaskBO;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface TaskService {

    List<TaskBO> getAllTasks() ;

    TaskBO getTaskById(Long id) ;

    TaskBO getTaskByTitle(String title) ;

    void saveOrUpdateTask(TaskBO task) ;

    void deleteTask(Long id) ;

}
