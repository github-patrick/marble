package com.example.marble.controllers;

import com.example.marble.domain.dtos.StudentDto;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.service.StudentService;
import com.example.marble.service.TeacherService;
import com.example.marble.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = StudentApiController.RESOURCE_PATH)
public class StudentApiController {

    public static final String RESOURCE_PATH = "/teachers/{teacherId}/students";

    private UniversityService universityService;
    private TeacherService teacherService;
    private StudentService studentService;

    @Autowired
    public StudentApiController(UniversityService universityService, TeacherService teacherService, StudentService studentService) {
        this.universityService = universityService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@PathVariable Long teacherId, @RequestBody StudentDto studentDto) {
        TeacherDto teacherDto = teacherService.getOneTeacher(teacherId);

        StudentDto studentDtoSaved = studentService.createStudent(teacherDto, studentDto);

        return new ResponseEntity(studentDtoSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> displayAllStudents(@PathVariable Long teacherId) {
        TeacherDto teacherDto = teacherService.getOneTeacher(teacherId);

        List<StudentDto> studentDtoList = studentService.getAllStudents(teacherDto);

        return new ResponseEntity (studentDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long teacherId, @PathVariable Long studentId) {
        TeacherDto teacherDto = teacherService.getOneTeacher(teacherId);

        StudentDto studentDto = studentService.getStudent(teacherDto, studentId);

        return new ResponseEntity(studentDto, HttpStatus.OK);
    }

    @PutMapping(value = "{studentId}")
    public ResponseEntity updateStudent(@PathVariable Long teacherId, @PathVariable Long studentId, @Valid @RequestBody
            StudentDto studentDto) {

        studentDto.setId(studentId);
        studentDto.getTeachers().add(teacherService.getOneTeacher(teacherId));
        studentService.updateStudent(studentDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "{studentId}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long teacherId, @PathVariable Long studentId,
                                                   @RequestBody StudentDto studentDto) {
        teacherService.getOneTeacher(teacherId);

        return new ResponseEntity(studentService.patchStudent(studentId, studentDto), HttpStatus.OK);
    }


}
