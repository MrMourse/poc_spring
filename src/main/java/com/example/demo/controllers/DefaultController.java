package com.example.demo.controllers;

import com.example.demo.models.user.UserBO;
import com.example.demo.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    //Verification de la disponibilité des services.
    public ResponseEntity<String> pong() {
        logger.info("Boot services OK .....");
        return new ResponseEntity<>("Server : " + HttpStatus.OK.name(), HttpStatus.OK);
    }
}