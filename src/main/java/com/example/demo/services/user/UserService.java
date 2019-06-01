package com.example.demo.services.user;

import com.example.demo.exceptions.BusinessResourceException;
import com.example.demo.models.user.UserBO;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface UserService {

    List<UserBO> getAllUsers() throws BusinessResourceException;

    UserBO getUserById(Long id) throws BusinessResourceException;

    UserBO getUserByName(String name) throws BusinessResourceException;

    UserBO saveOrUpdateUser(UserBO user) throws BusinessResourceException;

    void deleteUser(Long id) throws BusinessResourceException;

}