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

/**
 * Permet la mise à disposition des services gérant les tâches.
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/tasks")
public class TaskController {

    /**
     * Permet l'utilisation de logBack à travers le logger.
     */
    private static final Logger logger =
            LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    /**
     * Initialisation du task service.
     * @param taskService, service gérant les tâches.
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Permet la récupération de toutes les tâches.
     * En cas d'échec renvoit une liste vide.
     *
     * @return ResponseEntity<ResponseTaskDTO>
     */
    @GetMapping(value = "")
    public ResponseEntity<ResponseTaskDTO> getAllTasks() {
        List<TaskBO> tasks = taskService.getAllTasks();
        List<TaskDTO> tasksDTO = TaskMapper.INSTANCE.bosToDtos(tasks);
        String logInfo = String.format("Task list : %1$s.", tasks.toString());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, tasksDTO);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     *
     * Permet la récupération d'une seule tâche à partir de son id.
     * En cas d'échec renvoit une liste vide.
     *
     * @param id, id d'une tâche.
     * @return ResponseEntity<ResponseTaskDTO>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDTO> getTask(@PathVariable(value = "id") Long id) {
        TaskBO taskToGet = taskService.getTaskById(id);
        TaskDTO taskDTO = TaskMapper.INSTANCE.boToDto(taskToGet);
        String logInfo = String.format("Task Update : %1$s.", taskToGet.getTitle());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Permet la récupération d'une seule tâche à partir de son titre.
     * En cas d'échec renvoit une liste vide.
     *
     * @param title, titre d'une tâche.
     * @return ResponseEntity<ResponseTaskDTO>
     */
    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ResponseTaskDTO> getTaskByTitle(@PathVariable(value = "title") String title) {
        TaskBO taskToGet = taskService.getTaskByTitle(title);
        TaskDTO taskDTO = TaskMapper.INSTANCE.boToDto(taskToGet);
        String logInfo = String.format("Task Found : %1$s.", taskToGet.getTitle());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Permet la sauvegarde d'une tâche dans la base de données.
     *
     * @param task, tâche à sauvegarder.
     * @return ResponseEntity<ResponseTaskDTO>
     */
    @PostMapping(value = "")
    public ResponseEntity<ResponseTaskDTO> saveTask(@RequestBody TaskDTO task) {
        TaskBO taskToInsert = TaskMapper.INSTANCE.dtoToBo(task);
        TaskBO taskInserted = taskService.saveOrUpdateTask(taskToInsert);
        TaskDTO taskToSend = TaskMapper.INSTANCE.boToDto(taskInserted);
        String logInfo = String.format("Task Saved : %1$s.", taskToSend.toString());
        logger.info(logInfo);
        ResponseTaskDTO response = new ResponseTaskDTO(StatusJSEND.SUCCESS, Collections.singletonList(taskToSend));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Permet la modification d'une tâche dans la base de données.
     * En cas d'erreur sur l'id, une nouvelle tâche est crée.
     *
     * @param id, id de la tâche.
     * @param task, la tâche à modifier.
     * @return ResponseEntity<ResponseTaskDTO>
     */
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

    /**
     * Permet la suppression d'une tâche.
     * En cas d'échec renvoit une liste vide.
     *
     * @param id, id de la tâche à supprimer.
     * @return ResponseEntity<ResponseDTO>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable(value = "id") Long id) {
        taskService.deleteTask(id);
        ResponseDTO response = new ResponseDTO(StatusJSEND.SUCCESS,"Deletion done.");
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}
