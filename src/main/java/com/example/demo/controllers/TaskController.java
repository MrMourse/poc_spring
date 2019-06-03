package com.example.demo.controllers;

import com.example.demo.models.mapper.TaskMapper;
import com.example.demo.models.response.ResponseDTO;
import com.example.demo.models.response.ResponseTaskDTO;
import com.example.demo.models.response.StatusJSEND;
import com.example.demo.models.task.TaskBO;
import com.example.demo.models.task.TaskDTO;
import com.example.demo.services.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "")
    public ResponseEntity<ResponseTaskDTO> getAllTasks() {
        List<TaskBO> tasks = taskService.getAllTasks();
        List<TaskDTO> tasksDTO = TaskMapper.INSTANCE.bosToDtos(tasks);
        String logInfo = String.format("Task list : %1$s.", tasks.toString());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, tasksDTO);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDTO> getTask(@PathVariable(value = "id") Long id) {
        TaskBO taskToGet = taskService.getTaskById(id);
        TaskDTO taskDTO = TaskMapper.INSTANCE.boToDto(taskToGet);
        String logInfo = String.format("Task Update : %1$s.", taskToGet.getTitle());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);


    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ResponseTaskDTO> getTaskByTitle(@PathVariable(value = "title") String title) {
        TaskBO taskToGet = taskService.getTaskByTitle(title);
        TaskDTO taskDTO = TaskMapper.INSTANCE.boToDto(taskToGet);
        String logInfo = String.format("Task Found : %1$s.", taskToGet.getTitle());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);

    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseTaskDTO> saveTask(@RequestBody TaskDTO task) {
        TaskBO taskToInsert = TaskMapper.INSTANCE.dtoToBo(task);
        TaskBO taskInserted = taskService.saveOrUpdateTask(taskToInsert);
        TaskDTO taskToSend = TaskMapper.INSTANCE.boToDto(taskInserted);
        logger.info("userSave : " + taskToSend.toString());
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskToSend));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDTO> updateTask(@PathVariable(value = "id") Long id,
                                                      @RequestBody TaskDTO task) {
        task.setIdUpdate(id);
        TaskBO taskToUpdate = TaskMapper.INSTANCE.dtoToBo(task);
        TaskBO taskUpdated = taskService.saveOrUpdateTask(taskToUpdate);
        TaskDTO taskToSend = TaskMapper.INSTANCE.boToDto(taskUpdated);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskToSend));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable(value = "id") Long id) {
        taskService.deleteTask(id);
        ResponseDTO response = new ResponseDTO(StatusJSEND.SUCCESS,"Deletion done.");
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}
