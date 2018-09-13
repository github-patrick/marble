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

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Tommy");
        teacher2.setLastName("Forster");
        teacher2.setUniversity(university);
        teacher2.setStartDate(new Date());

        Student student = new Student();
        student.setFirstName("Patrick");
        student.setLastName("Ugwu");
        student.setCourse("Computer Science");
        student.setDegreeType(DegreeType.PhD);

        teacher.getStudents().add(student);


        Teacher teach = teacherRepository.save(teacher);
        teacherRepository.save(teacher2);

        System.out.println(teach);

        List<Teacher> t = teacherRepository.findByUniversity(university);

        Student s = studentRepository.findById(1l).get();



        log.info("-------------------------------");
    }
}
