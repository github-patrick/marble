package com.example.marble.controllers;

import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.mappers.TeacherMapper;
import com.example.marble.service.TeacherService;
import com.example.marble.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(TeacherApiController.RESOURCE_PATH)
public class TeacherApiController {

    public static final String RESOURCE_PATH = "/universities/{universityId}/teachers";
    private TeacherService teacherService;

    private UniversityService universityService;

    @Autowired
    public TeacherApiController(TeacherService teacherService, UniversityService universityService) {
        this.teacherService = teacherService;
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody @Valid TeacherDto teacherDto, @PathVariable Long universityId) {

        UniversityDto universityDto = universityService.getUniversity(universityId);
        teacherDto.setUniversity(universityDto);

        TeacherDto teacherSavedDto = teacherService.createTeacher(teacherDto);

        return new ResponseEntity(teacherSavedDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "{teacherId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long universityId, @PathVariable Long teacherId) {
        UniversityDto universityDto = universityService.getUniversity(universityId);

        TeacherDto teacherDto = teacherService.getTeacher(universityDto, teacherId);

        return new ResponseEntity(teacherDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> displayAllTeachers(@PathVariable Long universityId) {
        UniversityDto universityDto = universityService.getUniversity(universityId);

        List<TeacherDto> teacherDtoList = teacherService.getAllTeachers(universityDto);

        return new ResponseEntity (teacherDtoList, HttpStatus.OK);
    }

}
