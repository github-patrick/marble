package com.example.marble.controllers;

import com.example.marble.domain.University;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.mappers.UniversityMapper;
import com.example.marble.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UniversityApiController.RESOURCE_PATH)
public class UniversityApiController {

    public static final String RESOURCE_PATH = "/universities";


    // GET /universities/
    // Get all universities

    // GET /university/{id}

    // POST /university

    // DEL /university/{id}

    private UniversityMapper universityMapper;
    private UniversityService universityService;


    @Autowired
    public UniversityApiController(UniversityMapper universityMapper, UniversityService universityService) {
        this.universityMapper = universityMapper;
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<UniversityDto> createUniversity(@RequestBody @Valid UniversityDto universityDto) {

        UniversityDto universitySavedDto = universityService.createUniversity(universityDto);

        return new ResponseEntity(universitySavedDto, HttpStatus.CREATED);
    }


    @GetMapping(value = "{id}")
    public ResponseEntity<UniversityDto> displayUniversity(@PathVariable Long id) {

        UniversityDto universityDto = universityService.getUniversity(id);
        return new ResponseEntity(universityDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UniversityDto>> displayAllUniversities() {
        return new ResponseEntity(universityService.getAllUniversities(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity updateUniversity(@PathVariable Long id, @Valid @RequestBody UniversityDto universityDto) {

        universityDto.setId(id);
        universityService.updateUniversity(universityDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
