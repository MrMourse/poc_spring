package com.example.demo.controllers;

import com.example.demo.models.user.UserBO;
import com.example.demo.services.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@WebAppConfiguration
public class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final UserBO user = new UserBO("test","Dupont");

    @Before
    public void setUp() {
        //Initialisation du setup avant chaque test
        List<UserBO> allUsers = Collections.singletonList(user);

        // Mock de la couche de service
        given(userService.getAllUsers()).willReturn(allUsers);
        when(userService.getUserById(any(Long.class))).thenReturn(user);
        when(userService.saveOrUpdateUser(any(UserBO.class))).thenReturn(user);
    }

    @Test
    public void testFindAllUsers(){

        try {
            mockMvc.perform(get("/users/")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())    //statut HTTP de la réponse
                    .andExpect(jsonPath("$.data").value(hasSize(1)))
                    .andExpect(jsonPath("$.data[0].name").value(user.getName()))
                    .andReturn();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //on s'assure que la méthode de service getAllUsers() a bien été appelée
        verify(userService).getAllUsers();
    }

    @Test
    public void testSaveUser() {

        given(userService.getUserByName("Dupont")).willReturn(null);
        //on exécute la requête
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"testa\",\n" +
                            "    \"mail\": \"test@gmail.com\"\n" +
                            "}"))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        //on s'assure que la méthode de service saveOrUpdateUser(User) a bien été appelée
        verify(userService).saveOrUpdateUser(any(UserBO.class));

    }

    @Test
    public void testDeleteUser(){
        // on exécute le test
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 1))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        // On vérifie que la méthode de service deleteUser(Id) a bien été appelée
        verify(userService).deleteUser(any(Long.class));
    }

    @Test
    public void testUpdateUser(){

        //on exécute la requête
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}",1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"testa\",\n" +
                            "    \"mail\": \"test@gmail.com\"\n" +
                            "}"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        //on s'assure que la méthode de service saveOrUpdateUser(User) a bien été appelée
        verify(userService).saveOrUpdateUser(any(UserBO.class));

    }
}