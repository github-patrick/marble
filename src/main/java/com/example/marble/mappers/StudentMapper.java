package com.example.marble.mappers;

import com.example.marble.domain.Student;
import com.example.marble.domain.dtos.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private ModelMapper modelMapper;

    @Autowired
    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentDto map(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    public Student map(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        return student;
    }
}
