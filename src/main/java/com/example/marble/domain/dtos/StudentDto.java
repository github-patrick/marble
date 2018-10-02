package com.example.marble.domain.dtos;

import com.example.marble.domain.Address;
import com.example.marble.domain.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@ToString(exclude = "teachers")
public class StudentDto {

    private Long id;

    @NotEmpty
    @NotNull
    private String firstName;

    @NotEmpty
    @NotNull
    private String lastName;

    @Email
    private String email;

    private Address address;


    private CourseDto course;

    @JsonIgnore
    private List<TeacherDto> teachers = new ArrayList<>();
}
