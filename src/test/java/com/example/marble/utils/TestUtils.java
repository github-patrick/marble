package com.example.marble.utils;

import com.example.marble.domain.University;
import com.example.marble.domain.dtos.UniversityDto;

public class TestUtils {

    public static University getDefaultUniversity() {
        University university = new University();
        university.setName("Kings College");
        return university;
    }

    public static UniversityDto getDefaultUniversityDto() {
        UniversityDto universityDto = new UniversityDto();
        universityDto.setName("Kings College");
        return universityDto;
    }
}
