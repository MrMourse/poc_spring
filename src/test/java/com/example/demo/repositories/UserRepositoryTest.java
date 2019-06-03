package com.example.demo.repositories;

import com.example.demo.models.user.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)//permet d'établir une liaison entre JUnit et Spring
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private final UserEntity user = new UserEntity("user2","mail1@gmail.com");

    @Before
    public void setup(){
        entityManager.persist(user);//on sauvegarde l'objet user au début de chaque test
        entityManager.flush();
    }
    @Test
    public void testFindAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        assertEquals(1, users.size());
    }

    @Test
    public void testSaveUser(){
        UserEntity userToSave = new UserEntity("Paul","mail1@gmail.com");
        UserEntity userSaved =  userRepository.save(userToSave);
        assertEquals("Paul", userSaved.getName());
    }

    @Test
    public void testDeleteUser(){
        userRepository.deleteById(user.getId());
        Optional<UserEntity> userFromDB = userRepository.findByName(user.getName());
        assertFalse(userFromDB.isPresent());
    }

    @Test
    public void testUpdateUser() {
        Optional<UserEntity> userToUpdate = userRepository.findByName(user.getName());
        if (userToUpdate.isPresent()){
            userToUpdate.get().setName("user3");
            userRepository.save(userToUpdate.get());
            Optional<UserEntity> userUpdatedFromDB = userRepository.findByName(userToUpdate.get().getName());
            assertNotNull(userUpdatedFromDB);
        }
    }
}