package com.example.marble.domain.dtos;

import com.example.marble.domain.Teacher;
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

    private boolean funded;


}
