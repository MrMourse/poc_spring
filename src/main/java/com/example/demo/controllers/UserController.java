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

/**
 * Permet la mise à disposition des services gérant les utilisateurs.
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    /**
     * Permet l'utilisation de logBack à travers le logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Initialisation du user service.
     * @param userService, service de gestion d'utilisateur.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Permet la récupération de tous les utilisateurs.
     * En cas d'échec renvoit une liste vide.
     *
     * @return ResponseEntity<ResponseUserDTO>
     */
    @GetMapping(value = "")
    public ResponseEntity<ResponseUserDTO> getAllUsers() {
        List<UserBO> users = userService.getAllUsers();
        List<UserDTO> usersDTO = UserMapper.INSTANCE.bosToDtos(users);
        String logInfo = String.format("Users List : %1$s.", users.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS, usersDTO);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Permet la récupération d'un seul utilisateur à partir de son id.
     * En cas d'échec renvoit une liste vide.
     *
     * @param id, id de l'utilisateur.
     * @return ResponseEntity<ResponseUserDTO
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> getUser(@PathVariable(value = "id") Long id) {
        UserBO userToGet = userService.getUserById(id);
        UserDTO userDTO = UserMapper.INSTANCE.boToDto(userToGet);
        String logInfo = String.format("User Found By Id : %1$s.", userDTO.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS,
                Collections.singletonList(userDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Permet la récupération d'un seul utilisateur à partir de son nom.
     * En cas d'échec renvoit une liste vide.
     *
     * @param name, nom de l'utilisateur
     * @return ResponseEntity<ResponseUserDTO>
     */
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<ResponseUserDTO> getUserByName(@PathVariable(value = "name") String name) {
        UserBO userToGet = userService.getUserByName(name);
        UserDTO userDTO = UserMapper.INSTANCE.boToDto(userToGet);
        String logInfo = String.format("User Found By Name : %1$s.", userDTO.toString());
        logger.info(logInfo);
        ResponseUserDTO response = new ResponseUserDTO(StatusJSEND.SUCCESS,
                Collections.singletonList(userDTO));
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Permet la sauvegarde d'un utilisateur dans la base de données.
     *
     * @param user, l'utilisateur à sauvegarder.
     * @return ResponseEntity<ResponseUserDTO>
     */
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

    /**
     * Permet la modification d'un utilisateur dans la base de données.
     * En cas d'erreur sur l'id, une nouvel utilisateur est créé.
     *
     * @param id, de l'utilisateur à modifier.
     * @param user, utilisateur à modifier.
     * @return ResponseEntity<ResponseUserDTO>
     */
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

    /**
     * Permet la suppression d'un utilisateur.
     * En cas d'échec renvoit une liste vide.
     *
     * @param id, de l'utilisateur à supprimer.
     * @return ResponseEntity<ResponseDTO>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        ResponseDTO response = new ResponseDTO(StatusJSEND.SUCCESS,"deletion done.");
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}