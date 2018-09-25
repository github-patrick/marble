package com.example.marble.utils.helper;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.domain.dtos.StudentDto;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.domain.dtos.UniversityDto;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestUtils {

    private static DataFactory dataFactory = new DataFactory();


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
        teacher.setFirstName(dataFactory.getFirstName());
        teacher.setLastName(dataFactory.getLastName());
        teacher.setNationality("Brazilian");
        teacher.setPosition("IT Administrator");
        teacher.setStartDate(new Date());
        teacher.setUniversity(getDefaultUniversity());

        return teacher;
    }

    public static TeacherDto getDefaultTeacherDto() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName(dataFactory.getFirstName());
        teacherDto.setLastName(dataFactory.getLastName());
        teacherDto.setNationality("Brazilian");
        teacherDto.setPosition("IT Administrator");
        teacherDto.setStartDate(new Date());
        teacherDto.setUniversity(getDefaultUniversityDto());

        return teacherDto;
    }

    public static StudentDto getDefaultStudentDto() {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(dataFactory.getFirstName());
        studentDto.setLastName(dataFactory.getLastName());
        studentDto.setEmail(dataFactory.getEmailAddress());
        studentDto.getTeachers().add(getDefaultTeacherDto());

        return studentDto;
    }
}
