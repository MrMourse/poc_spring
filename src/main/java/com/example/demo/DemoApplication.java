package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DemoApplication {
    //TODO : Check Error (Enum, controle des erreurs possibles)
    //TODO : Check Tests
    //TODO : Implement test on tasks

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
