package com.example.marble;

import com.example.marble.domain.University;
import com.example.marble.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MarbleApplication {


    public static void main(String[] args) {
        SpringApplication.run(MarbleApplication.class, args);
    }
}
