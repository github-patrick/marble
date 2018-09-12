package com.example.marble;

import com.example.marble.domain.Student;
import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.domain.enums.DegreeType;
import com.example.marble.repository.StudentRepository;
import com.example.marble.repository.TeacherRepository;
import com.example.marble.repository.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Component
public class UniversityRepositoryCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    private static final Logger log = LoggerFactory.getLogger(UniversityRepositoryCommandLineRunner.class);


    @Override
    public void run(String... args) throws Exception {

        University university = new University();
        university.setName("Cambridge");
        university.setCreatedAt(new Date());
        universityRepository.save(university);
        log.info("-------------------------------");

        Teacher teacher = new Teacher();
        teacher.setFirstName("Sarah");
        teacher.setLastName("Jane");
        teacher.setUniversity(university);
        teacher.setStartDate(new Date());
        teacherRepository.save(teacher);

        Student student = new Student();
        student.setFirstName("Patrick");
        student.setLastName("Ugwu");
        student.setCourse("Computer Science");
        student.setDegreeType(DegreeType.PhD);
        student.getTeachers().add(teacher);
        studentRepository.save(student);
        ;

        log.info("-------------------------------");
    }
}
