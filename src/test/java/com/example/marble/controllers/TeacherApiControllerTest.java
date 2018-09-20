package com.example.marble.controllers;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.exception.CustomisedResponseEntityExceptionHandler;
import com.example.marble.mappers.TeacherMapper;
import com.example.marble.service.TeacherService;
import com.example.marble.service.UniversityService;
import com.example.marble.utils.helper.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TeacherApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TeacherService teacherService;

    @Mock
    private UniversityService universityService;

    private TeacherApiController teacherApiController;

    @Before
    public void setUp() {
        teacherApiController = new TeacherApiController(teacherService, universityService);
        mockMvc = MockMvcBuilders.standaloneSetup(teacherApiController).setControllerAdvice(
                CustomisedResponseEntityExceptionHandler.class).build();
    }


    @Test
    public void createTeacher() throws Exception {

        TeacherDto teacherDto = TestUtils.getDefaultTeacherDto();

        when(universityService.getUniversity(1l)).thenReturn(TestUtils.getDefaultUniversityDto());
        when(teacherService.createTeacher(teacherDto)).thenReturn(teacherDto);

        mockMvc.perform(post(TeacherApiController.RESOURCE_PATH, "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(teacherDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(teacherDto.getFirstName())));


    }

    @Test
    public void getTeacher() throws Exception {

        TeacherDto teacherDto = TestUtils.getDefaultTeacherDto();
        UniversityDto universityDto = TestUtils.getDefaultUniversityDto();

        when(universityService.getUniversity(1l)).thenReturn(universityDto);
        when(teacherService.getTeacher(universityDto, 1l)).thenReturn(teacherDto);

        mockMvc.perform(get(TeacherApiController.RESOURCE_PATH + "/1", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(teacherDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(teacherDto.getLastName())));



    }
}