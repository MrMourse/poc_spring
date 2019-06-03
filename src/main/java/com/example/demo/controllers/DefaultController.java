package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    public DefaultController() {
        super();
    }

    /**
     *
     * Vérifie la disponibilité des services.
     * @return ResponseEntity<String>
     */
    @GetMapping(value = "/")
    public ResponseEntity<String> pong() {
        logger.info("Boot services OK .....");
        return new ResponseEntity<>("Server : " + HttpStatus.OK.name(), HttpStatus.OK);
    }
}