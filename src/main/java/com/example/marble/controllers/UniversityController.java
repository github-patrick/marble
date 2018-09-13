package com.example.marble.controllers;

import com.example.marble.domain.University;
import com.example.marble.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityController {


    // GET /universities/
    // Get all universities

    // GET /university/{id}

    // POST /university

    // DEL /university/{id}

    private UniversityRepository universityRepository;

    @Autowired
    public UniversityController(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @GetMapping(value = "/university")
    public University displayUniversity() {
        University university = new University();
        university.setFunded(true);

        University university1 = universityRepository.findById(1l).get();

        return university1;
    }



}
