package com.example.marble.repository;

import com.example.marble.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {

    boolean existsByName(String name);

    Optional<University> findFirstByOrderByCreatedAt();

}
