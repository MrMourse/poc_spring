package com.example.demo.services.user;

import com.example.demo.models.user.UserBO;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Service user permettant la gestion de la couche métier.
 */
@Configuration
public interface UserService {

    List<UserBO> getAllUsers() ;

    UserBO getUserById(Long id) ;

    UserBO getUserByName(String name) ;

    UserBO saveOrUpdateUser(UserBO user) ;

    void deleteUser(Long id) ;

}