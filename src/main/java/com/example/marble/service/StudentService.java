package com.example.marble.service;

import com.example.marble.domain.Student;
import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.domain.dtos.StudentDto;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.exception.ErrorMessages;
import com.example.marble.exception.StudentNotFoundException;
import com.example.marble.exception.StudentNotFoundForTeacherException;
import com.example.marble.exception.TeacherNotFoundForUniversityException;
import com.example.marble.mappers.StudentMapper;
import com.example.marble.mappers.TeacherMapper;
import com.example.marble.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private TeacherMapper teacherMapper;
    private TeacherService teacherService;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, TeacherMapper teacherMapper, TeacherService teacherService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
        this.teacherService = teacherService;
    }

    public StudentDto createStudent(TeacherDto teacherDto, StudentDto studentDto) {

        Student student = studentMapper.map(studentDto);
        StudentDto studentDtoSaved = studentMapper.map(studentRepository.save(student));

        teacherDto.getStudents().add(studentDtoSaved);
        teacherService.updateTeacher(teacherDto);

        return studentDtoSaved;

    }

    public List<StudentDto> getAllStudents(TeacherDto teacherDto) {

        Teacher teacher = teacherMapper.map(teacherDto);
        List<StudentDto> studentDtos = new ArrayList<>();

        Iterator<Student> iterator = studentRepository.findByTeachers(teacher).iterator();

        while (iterator.hasNext()) {
            studentDtos.add(studentMapper.map(iterator.next()));
        }
        return studentDtos;
    }


    public StudentDto getStudent(TeacherDto teacherDto, Long studentId) {
        Teacher teacher = teacherMapper.map(teacherDto);

        if (!studentRepository.findStudentsByTeachersAndId(teacher, studentId).isPresent()) {
            throw new StudentNotFoundForTeacherException(ErrorMessages.STUDENT_NOT_FOUND_FOR_UNIVERSITY);
        }

        StudentDto studentDto = studentMapper.map(studentRepository.findStudentsByTeachersAndId(teacher, studentId).get());
        return studentDto;

    }

    public void updateStudent(StudentDto studentDto) {
        Student student = studentMapper.map(studentDto);
        studentRepository.save(student);
    }

    public StudentDto patchStudent(Long studentId, StudentDto studentDto) {

        if (!studentRepository.findById(studentId).isPresent()) {
            throw new StudentNotFoundException(ErrorMessages.STUDENT_NOT_FOUND);
        }

        StudentDto studentRetrievedDto = studentMapper.map(studentRepository.findById(studentId).get());

        if (studentDto.getFirstName() != null) {
            studentRetrievedDto.setFirstName(studentDto.getFirstName());
        }
        if (studentDto.getLastName() != null) {
            studentRetrievedDto.setLastName(studentDto.getLastName());
        }
        if (studentDto.getTeachers() != null) {
            studentDto.setTeachers(studentDto.getTeachers());
        }
        if (studentDto.getEmail() != null) {
            studentRetrievedDto.setEmail(studentDto.getEmail());
        }
        if (studentDto.getCourse() != null) {
            studentRetrievedDto.setCourse(studentDto.getCourse());
        }
        if (studentDto.getAddress() != null) {
            studentRetrievedDto.setAddress(studentDto.getAddress());
        }

        Student studentDtoSaved = studentRepository.save(studentMapper.map(studentRetrievedDto));


        return studentMapper.map(studentDtoSaved);

    }
}
