package com.example.marble.service;

import com.example.marble.domain.University;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.exception.ErrorMessages;
import com.example.marble.exception.ErrorResponse;
import com.example.marble.exception.UniversityExistsException;
import com.example.marble.exception.UniversityNotFoundException;
import com.example.marble.mappers.UniversityMapper;
import com.example.marble.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UniversityService {

    private UniversityRepository universityRepository;
    private UniversityMapper universityMapper;

    @Autowired
    public UniversityService(UniversityRepository universityRepository, UniversityMapper universityMapper) {
        this.universityRepository = universityRepository;
        this.universityMapper = universityMapper;
    }

    public UniversityDto createUniversity(UniversityDto universityDto) {

        if (universityRepository.existsByName(universityDto.getName())) {
            throw new UniversityExistsException(ErrorMessages.UNIVERSITY_EXISTS);
        }

        University university = universityMapper.map(universityDto);
        University universitySaved = universityRepository.save(university);
        UniversityDto universitySavedDto = universityMapper.map(universitySaved);
        return universitySavedDto;
    }

    public UniversityDto getUniversity(Long id) {
        if (!universityRepository.findById(id).isPresent()) {
            throw new UniversityNotFoundException(ErrorMessages.UNIVERSITY_NOT_FOUND);
        }
        UniversityDto universityDto = universityMapper.map(universityRepository.findById(id).get());
        return universityDto;
    }


    public List<UniversityDto> getAllUniversities() {
        List<UniversityDto> universityDtos = new ArrayList<>();
        Iterator<University> iterator = universityRepository.findAll().iterator();

        while (iterator.hasNext()) {
            universityDtos.add(universityMapper.map(iterator.next()));
        }

        return universityDtos;

    }

    public void updateUniversity(UniversityDto universityDto) {
        if (universityRepository.existsByName(universityDto.getName())) {
            throw new UniversityExistsException(ErrorMessages.UNIVERSITY_EXISTS);
        }

        University university = universityMapper.map(universityDto);
        universityRepository.save(university);
    }
}
