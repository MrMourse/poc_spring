package com.example.demo.integration;

import com.example.demo.models.response.ResponseDTO;
import com.example.demo.models.response.ResponseUserDTO;
import com.example.demo.models.user.UserDTO;
import com.example.demo.models.user.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerIntegrationTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort //permet d'utiliser le port local du serveur, sinon une erreur "Connection refused"
    private int port;

    private static final String URL= "http://localhost:";//url du serveur REST. Cette URL peut être celle d'un serveur distant

    private String getURLWithPort(String uri) {
        return URL + port + uri;
    }

    private static String good = "Good :";
    @Test
    public void testFindAllUsers() {
        ResponseEntity<ResponseUserDTO> responseEntity = restTemplate.getForEntity(getURLWithPort("/users"), ResponseUserDTO.class);

        List<UserDTO> userCollections = Objects.requireNonNull(responseEntity.getBody()).getData();


        // On vérifie le code de réponse HTTP, en cas de différence entre les deux valeurs, le message "Réponse inattendue" est affiché
        assertEquals(good, HttpStatus.FOUND.value(), responseEntity.getStatusCodeValue());

        assertNotNull(userCollections);
        logger.info("Utilisateur trouvé : " + userCollections.toString());
        assertEquals(1, userCollections.size());//on a bien 3 utilisateurs initialisés par les scripts data.sql au démarrage des tests
    }

    @Test
    public void testSaveUser() {
        UserEntity user = new UserEntity("PIPO");
        ResponseEntity<ResponseUserDTO> userEntitySaved =  restTemplate.postForEntity(getURLWithPort("/users/"), user, ResponseUserDTO.class);
        UserDTO userSaved = Objects.requireNonNull(userEntitySaved.getBody()).getData().get(0);
        assertNotNull(userSaved);
        assertEquals(user.getName(),userSaved.getName());
        assertEquals(good, HttpStatus.CREATED.value(), userEntitySaved.getStatusCodeValue());
    }

    @Test
    public void testDeleteUser() {
        Map<String, Long> variables = new HashMap<>(1);
        variables.put("id", 1L);
        ResponseEntity<ResponseDTO> responseEntity = restTemplate.exchange(getURLWithPort("/users/{id}"),
                HttpMethod.DELETE,
                null,
                ResponseDTO.class,
                variables);
        assertEquals(good, HttpStatus.GONE.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void testUpdateUser() {
        Map<String, Long> variables = new HashMap<>(2);
        UserEntity userToUpdate = new UserEntity("updateName");

        variables.put("id", 1L);

        HttpEntity<UserEntity> requestEntity = new HttpEntity<>(userToUpdate);

        ResponseEntity<ResponseUserDTO> responseEntity = restTemplate.exchange(getURLWithPort("/users/{id}"),
                HttpMethod.PUT,
                requestEntity,
                ResponseUserDTO.class,
                variables);
        assertEquals(good, HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }
}