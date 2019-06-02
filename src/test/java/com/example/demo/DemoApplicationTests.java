package com.example.demo;

import com.example.demo.controllers.TaskController;
import com.example.demo.controllers.UserController;
import com.example.demo.models.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DemoApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private TaskController taskController;

    @Test
    public void contextLoads() {
        assertNotNull(userController);
        assertNotNull(taskController);
    }

}
