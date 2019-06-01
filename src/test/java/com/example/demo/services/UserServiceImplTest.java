package com.example.demo.services;

import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.user.UserService;
import com.example.demo.services.user.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration //création des beans nécessaires pour les tests
    static class UserServiceImplTestContextConfiguration {

        @Bean//bean de service
        public UserService userService () {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean //création d'un mockBean pour UserRepository
    private UserRepository userRepository;

    private final UserBO userBO = new UserBO("Dupont","mail1@gmail.com");

    @Test
    public void testFindAllUsers() {
        UserEntity user = new UserEntity("Dupont","mail1@gmail.com");
        List<UserEntity> allUsers = Collections.singletonList(user);
        List<UserBO> allUsersExpected = UserMapper.INSTANCE.entitiesToBOs(allUsers);
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);

        List<UserBO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(allUsersExpected, users);
        assertEquals(allUsersExpected.size(), users.size());
        verify(userRepository).findAll();
    }

    @Test
    public void testSaveUser() {
        UserEntity user = new UserEntity("Dupont","mail1@gmail.com");
        UserEntity userMock = new UserEntity("Dupont","mail1@gmail.com");
        Mockito.when(userRepository.save((user))).thenReturn(userMock);
        UserBO userSaved = userService.saveOrUpdateUser(userBO);
        assertNotNull(userSaved);
        assertEquals(userMock.getId(), userSaved.getId());
        assertEquals(userMock.getName(), userSaved.getName());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    public void testDelete() {
        UserEntity user = new UserEntity("Dupont","mail1@gmail.com");
        UserEntity userMock = new UserEntity("Dupont","mail1@gmail.com");
        Mockito.when(userRepository.save((user))).thenReturn(userMock);
        UserBO userSaved = userService.saveOrUpdateUser(userBO);
        assertNotNull(userSaved);
        assertEquals(userMock.getId(), userSaved.getId());
        userService.deleteUser(userSaved.getId());
        verify(userRepository).deleteById(any(Long.class));
    }

    @Test
    public void testUpdateUser() {
        UserBO userToUpdate = new UserBO("Paul", "mail");
        UserEntity userEntityToUpdate = UserMapper.INSTANCE.BOtoEntity(userToUpdate);
        Mockito.when(userRepository.save((userEntityToUpdate))).thenReturn(userEntityToUpdate);
        UserBO userFromDB = userService.saveOrUpdateUser(userToUpdate);
        assertNotNull(userFromDB);
        assertEquals(userToUpdate.getName(), userFromDB.getName());
        verify(userRepository).save(any(UserEntity.class));
    }
}