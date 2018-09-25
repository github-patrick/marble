package com.example.marble.utils.helper;

import com.example.marble.domain.Student;
import com.example.marble.domain.Teacher;
import com.example.marble.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentHelper {

    @Autowired
    private StudentRepository studentRepository;

    public Student getFirstStudentByTeacher(Teacher teacher) {
        return studentRepository.findByTeachers(teacher).get(0);

    }
}
