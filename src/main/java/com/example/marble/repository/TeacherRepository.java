package com.example.marble.repository;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findByUniversity(University university);

    Optional<Teacher> findTeacherByUniversityAndId(University university, Long id);

    Optional<Teacher> findFirstByOrderByCreatedAt();


}
