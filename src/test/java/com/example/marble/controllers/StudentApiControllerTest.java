package com.example.marble.controllers;

import com.example.marble.domain.dtos.StudentDto;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.exception.CustomisedResponseEntityExceptionHandler;
import com.example.marble.service.StudentService;
import com.example.marble.service.TeacherService;
import com.example.marble.service.UniversityService;
import com.example.marble.utils.helper.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentApiControllerTest {

    private MockMvc mockMvc;
    private StudentApiController studentApiController;
    @Mock
    private TeacherService teacherService;

    @Mock
    private UniversityService universityService;

    @Mock
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        studentApiController = new StudentApiController(universityService,teacherService, studentService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentApiController).setControllerAdvice(CustomisedResponseEntityExceptionHandler.class)
                .build();
    }

    @Test
    public void createTeacher() throws Exception {

        StudentDto studentDto = TestUtils.getDefaultStudentDto();
        TeacherDto teacherDto = TestUtils.getDefaultTeacherDto();

        when(teacherService.getOneTeacher(1l)).thenReturn(teacherDto);


        mockMvc.perform(post(StudentApiController.RESOURCE_PATH, "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}