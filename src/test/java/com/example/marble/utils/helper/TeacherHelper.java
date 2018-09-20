package com.example.marble.utils.helper;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TeacherHelper {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher getFirstTeacherByUniversity(University university) {
        return teacherRepository.findByUniversity(university).get(0);
    }

}
