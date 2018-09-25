package com.example.marble.mappers;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.dtos.TeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    private ModelMapper modelMapper;

    @Autowired
    public TeacherMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TeacherDto map(Teacher teacher) {
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        return teacherDto;
    }

    public Teacher map(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        return teacher;
    }


}
