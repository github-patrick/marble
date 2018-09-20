package com.example.marble.controllers;

import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.exception.CustomisedResponseEntityExceptionHandler;
import com.example.marble.mappers.UniversityMapper;
import com.example.marble.repository.UniversityRepository;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class UniversityApiControllerTest {

    private MockMvc mockMvc;
    private UniversityApiController universityApiController;

    @Mock
    private UniversityRepository universityRepository;

    @Mock
    private UniversityMapper universityMapper;

    @Mock
    private UniversityService universityService;

    @Before
    public void setUp() {
        universityApiController = new UniversityApiController(universityMapper, universityService);
        mockMvc = MockMvcBuilders.standaloneSetup(universityApiController)
                .setControllerAdvice(CustomisedResponseEntityExceptionHandler.class).build();
    }

    @Test
    public void createUniversity() throws Exception {

        UniversityDto universityDto = TestUtils.getDefaultUniversityDto();

        when(universityService.createUniversity(universityDto)).thenReturn(universityDto);


        mockMvc.perform(post(UniversityApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(universityDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(universityDto.getName())));
    }

    @Test
    public void displayUniversity() throws Exception {
        UniversityDto universityDto = TestUtils.getDefaultUniversityDto();

        when(universityService.getUniversity(1l)).thenReturn(universityDto);

        mockMvc.perform(get(UniversityApiController.RESOURCE_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(universityDto.getName())));
    }

    @Test
    public void displayAllUniversities() throws Exception {

        UniversityDto universityDto = TestUtils.getDefaultUniversityDto();
        universityDto.setName("Queen Mary");

        List<UniversityDto> list = Arrays.asList(TestUtils.getDefaultUniversityDto(),
                universityDto);

        when(universityService.getAllUniversities()).thenReturn(list);

        mockMvc.perform(get(UniversityApiController.RESOURCE_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[1].name", is("Queen Mary")));
    }

}