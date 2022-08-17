package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BackendApplication.class, args);
    }

}
