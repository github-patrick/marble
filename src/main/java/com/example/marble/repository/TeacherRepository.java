package com.example.marble.repository;

import com.example.marble.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
