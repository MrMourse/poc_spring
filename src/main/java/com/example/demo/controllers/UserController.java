package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;

import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.response.ResponseDTO;
import com.example.demo.models.response.ResponseUserDTO;
import com.example.demo.models.response.StatusJSEND;
import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserDTO;
import com.example.demo.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<ResponseUserDTO> getAllUsers() {
        List<UserBO> users = userService.getAllUsers();
        List<UserDTO> usersDTO = UserMapper.INSTANCE.bosToDtos(users);
        String logInfo = String.format("Users List : %1$s.", users.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS, usersDTO);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> getUser(@PathVariable(value = "id") Long id) {
        UserBO userToGet = userService.getUserById(id);
        UserDTO userDTO = UserMapper.INSTANCE.boToDto(userToGet);
        String logInfo = String.format("User Found By Id : %1$s.", userDTO.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS, Collections.singletonList(userDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<ResponseUserDTO> getUserByName(@PathVariable(value = "name") String name) {
        UserBO userToGet = userService.getUserByName(name);
        UserDTO userDTO = UserMapper.INSTANCE.boToDto(userToGet);
        String logInfo = String.format("User Found By Name : %1$s.", userDTO.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS, Collections.singletonList(userDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody UserDTO user) {
        UserBO userToInsert= UserMapper.INSTANCE.dtoToBo(user);
        UserBO userInserted = userService.saveOrUpdateUser(userToInsert);
        UserDTO userToSend = UserMapper.INSTANCE.boToDto(userInserted);
        String logInfo = String.format("User Saved : %1$s.", userToSend.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS, Collections.singletonList(userToSend));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable(value = "id") Long id,
                                              @RequestBody UserDTO user) {
        user.setIdUpdate(id);
        UserBO userToUpdate = UserMapper.INSTANCE.dtoToBo(user);
        UserBO userInserted = userService.saveOrUpdateUser(userToUpdate);
        UserDTO userToSend = UserMapper.INSTANCE.boToDto(userInserted);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS, Collections.singletonList(userToSend));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        ResponseDTO response = new ResponseDTO(StatusJSEND.SUCCESS,"deletion done.");
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}