package com.example.marble.domain.dtos;

import com.example.marble.domain.Student;
import com.example.marble.domain.enums.DegreeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString(exclude = "students")
public class CourseDto {

    private Long id;

    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

    @JsonIgnore
    private List<Student> students;
}
