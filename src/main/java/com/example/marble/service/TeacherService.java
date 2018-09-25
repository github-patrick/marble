package com.example.marble.service;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.exception.ErrorMessages;
import com.example.marble.exception.TeacherNotFoundForUniversityException;
import com.example.marble.mappers.TeacherMapper;
import com.example.marble.mappers.UniversityMapper;
import com.example.marble.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;
    private UniversityMapper universityMapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper, UniversityMapper
                          universityMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.universityMapper = universityMapper;
    }

    public TeacherDto createTeacher(TeacherDto teacherDto) {

        Teacher teacher = teacherMapper.map(teacherDto);
        Teacher teacherSaved = teacherRepository.save(teacher);
        return teacherMapper.map(teacherSaved);
    }

    public TeacherDto getTeacher(UniversityDto universityDto, Long id) {
        University university = universityMapper.map(universityDto);

        if (!teacherRepository.findTeacherByUniversityAndId(university, id).isPresent()) {
            throw new TeacherNotFoundForUniversityException(ErrorMessages.TEACHER_NOT_FOUND_FOR_UNIVERSITY);
        }
        TeacherDto teacherDto = teacherMapper.map(teacherRepository.findTeacherByUniversityAndId(university, id).get());
        return teacherDto;
    }

    public List<TeacherDto> getAllTeachers(UniversityDto universityDto) {
        University university = universityMapper.map(universityDto);
        List<TeacherDto> teacherDtos = new ArrayList<>();

        Iterator<Teacher> iterator = teacherRepository.findByUniversity(university).iterator();

        while (iterator.hasNext()) {
            teacherDtos.add(teacherMapper.map(iterator.next()));
        }
        return teacherDtos;
    }

    public void updateTeacher(TeacherDto teacherDto) {

        Teacher teacher = teacherMapper.map(teacherDto);
        teacherRepository.save(teacher);
    }

    public TeacherDto getOneTeacher(Long teacherId) {

        if (!teacherRepository.findById(teacherId).isPresent()) {
            throw new TeacherNotFoundForUniversityException(ErrorMessages.TEACHER_NOT_FOUND_FOR_UNIVERSITY);
        }
        Teacher teacher = teacherRepository.findById(teacherId).get();
        return teacherMapper.map(teacher);
    }
}
