package com.example.demo.repositories;

import com.example.demo.models.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Service taskRepository, permettant la gestion de l'objet task avec la base de données.
 */
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByTitle(String title);
}