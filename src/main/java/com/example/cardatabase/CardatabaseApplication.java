package com.example.cardatabase;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.logging.Logger;

@SpringBootApplication
public class CardatabaseApplication {
    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(CardatabaseApplication.class);
    public static void main(String[] args) {
        // After adding this comment the application is restarted
        SpringApplication.run(CardatabaseApplication.class, args);
        System.out.println("Hello Spring Boot");
    }

}
