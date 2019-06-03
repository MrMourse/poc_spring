package com.example.demo.repositories;

import com.example.demo.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Service userRepository, permettant la gestion de l'objet user avec la base de données.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}