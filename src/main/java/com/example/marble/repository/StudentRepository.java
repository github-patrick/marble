package com.example.marble.repository;

import com.example.marble.domain.Student;
import com.example.marble.domain.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByTeachers(Teacher teacher);

    Optional<Student> findStudentsByTeachersAndId(Teacher teacher, Long id);

}
