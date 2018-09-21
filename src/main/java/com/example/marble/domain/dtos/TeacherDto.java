package com.example.marble.domain.dtos;

import com.example.marble.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "university")
public class TeacherDto {


    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private Date startDate;

    @NotEmpty
    @NotNull
    private String nationality;

    @NotEmpty
    @NotNull
    private String position;

    @JsonIgnore
    private UniversityDto university;

    private List<Student> students = new ArrayList<>();
}
