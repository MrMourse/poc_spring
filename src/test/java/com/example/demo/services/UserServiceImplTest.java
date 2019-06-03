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

    private static final String NAME = "Dupont";
    private static final String MAIL = "mail1@gmail.com";

    private final UserBO userBO = new UserBO(NAME, MAIL);
    private final UserEntity user = new UserEntity(NAME, MAIL);

    @Test
    public void testFindAllUsers() {
        List<UserEntity> allUsers = Collections.singletonList(user);
        List<UserBO> allUsersExpected = UserMapper.INSTANCE.entitiesToBos(allUsers);
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);

        List<UserBO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(allUsersExpected, users);
        assertEquals(allUsersExpected.size(), users.size());
        verify(userRepository).findAll();
    }

    @Test
    public void testSaveUser() {
        UserEntity userMock = user;
        Mockito.when(userRepository.save((user))).thenReturn(userMock);
        userService.saveOrUpdateUser(userBO);
        assertNotNull(userMock);
        assertEquals(userMock.getId(), userMock.getId());
        assertEquals(userMock.getName(), userMock.getName());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    public void testDelete() {
        UserEntity userMock = user;
        Mockito.when(userRepository.save((user))).thenReturn(userMock);
        Mockito.when(userRepository.existsById(user.getId())).thenReturn(true);
        userService.saveOrUpdateUser(userBO);
        assertNotNull(userMock);
        assertEquals(userMock.getId(), userMock.getId());
        userService.deleteUser(userMock.getId());
        verify(userRepository).deleteById(any(Long.class));
    }

    @Test
    public void testUpdateUser() {
        UserBO userToUpdate = new UserBO("Paul", "mail");
        UserEntity userEntityToUpdate = UserMapper.INSTANCE.boToEntity(userToUpdate);
        Mockito.when(userRepository.save((userEntityToUpdate))).thenReturn(userEntityToUpdate);
        userService.saveOrUpdateUser(userToUpdate);
        assertNotNull(userEntityToUpdate);
        assertEquals(userToUpdate.getName(), userEntityToUpdate.getName());
        verify(userRepository).save(any(UserEntity.class));
    }
}