package com.example.marble;

import com.example.marble.domain.*;
import com.example.marble.domain.enums.DegreeType;
import com.example.marble.repository.StudentRepository;
import com.example.marble.repository.TeacherRepository;
import com.example.marble.repository.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

        Course course = Course.builder().name("Biology").degreeType(DegreeType.MA).build();

        University university = new University();
        university.setName("Cambridge");
        university.setCreatedAt(LocalDateTime.now());
        universityRepository.save(university);
        log.info("-------------------------------");

        Teacher teacher = new Teacher();
        teacher.setFirstName("Sarah");
        teacher.setLastName("Jane");
        teacher.setUniversity(university);
        teacher.setStartDate(new Date());
        teacher.setNationality("British");
        teacher.setPosition("Lecturer");
        teacher.setStudents(new ArrayList<>());

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Tommy");
        teacher2.setLastName("Forster");
        teacher2.setUniversity(university);
        teacher2.setStartDate(new Date());
        teacher2.setNationality("British");
        teacher2.setPosition("Lecturer");
        teacher2.setStudents(new ArrayList<>());


        Student student = new Student();
        student.setFirstName("Patrick");
        student.setLastName("Ugwu");
        student.setEmail("patrick.ugwu@sentiapps.com");
        student.setCourse(course);
        student.setAddress(Address.builder().addressLineOne("10 Downing Street").addressLineTwo("Westminister").postcode("SW10 ERT").build());

        Student student2 = new Student();
        student2.setFirstName("Jacob");
        student2.setLastName("Renes");
        student2.setCourse(course);

        student2.setAddress(Address.builder().addressLineOne("100 Hitchens").addressLineTwo("Enfield").postcode("SW11 ERT").build());


        teacher.getStudents().add(student);
        teacher.getStudents().add(student2);



        Teacher teach = teacherRepository.save(teacher);
        teacherRepository.save(teacher2);

        universityRepository.findById(1l).get();


        List<Teacher> teachers = teacherRepository.findByUniversity(university);



        log.info("-------------------------------");
    }
}
