package com.example.marble.domain.dtos;

import com.example.marble.domain.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class UniversityDto {

    private Long id;

    @NotNull
    @NotEmpty(message = "University name cannot be empty")
    private String name;

    private List<TeacherDto> teachers;

    @JsonIgnore
    private boolean funded;


}
