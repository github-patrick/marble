package com.example.marble.mappers;

import com.example.marble.domain.University;
import com.example.marble.domain.dtos.UniversityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniversityMapper {

    private ModelMapper modelMapper;

    @Autowired
    public UniversityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UniversityDto map(University university) {
        UniversityDto universityDto = modelMapper.map(university, UniversityDto.class);
        return universityDto;
    }

    public University map(UniversityDto universityDto) {
        University university = modelMapper.map(universityDto, University.class);
        return university;
    }
}
