package com.example.marble.utils.helper;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.University;
import com.example.marble.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniversityHelper {

    @Autowired
    private UniversityRepository universityRepository;

    public University getUniversityById(Long id) {

        if (!universityRepository.findById(id).isPresent()) {
            return new University();
        }
        return universityRepository.findById(id).get();
    }

    public University getFirstUniversity() {
        return universityRepository.findFirstByOrderByCreatedAt().get();
    }

}
