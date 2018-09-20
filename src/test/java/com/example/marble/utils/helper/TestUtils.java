package com.example.marble.utils.helper;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.domain.dtos.UniversityDto;

import java.util.Date;

public class TestUtils {

    public static University getDefaultUniversity() {
        University university = new University();
        university.setName("Kings College");
        return university;
    }

    public static UniversityDto getDefaultUniversityDto() {
        UniversityDto universityDto = new UniversityDto();
        universityDto.setName("Kings College");
        return universityDto;
    }

    public static Teacher getDefaultTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Lucy");
        teacher.setLastName("Noble");
        teacher.setStartDate(new Date());
        teacher.setUniversity(getDefaultUniversity());

        return teacher;
    }

    public static TeacherDto getDefaultTeacherDto() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName("Lucy");
        teacherDto.setLastName("Noble");
        teacherDto.setStartDate(new Date());
        teacherDto.setUniversity(getDefaultUniversityDto());

        return teacherDto;
    }
}
