package com.example.marble.bdd.api.config;

import com.example.marble.utils.helper.StudentHelper;
import com.example.marble.utils.helper.TeacherHelper;
import com.example.marble.utils.helper.TestUtils;
import com.example.marble.utils.helper.UniversityHelper;
import com.example.marble.utils.RequestData;
import com.example.marble.utils.ResponseData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestConfig {

    @Bean
    @Scope("cucumber-glue")
    public RequestData requestData() {
        return new RequestData();
    }

    @Bean
    @Scope("cucumber-glue")
    public ResponseData responseData() {
        return new ResponseData();
    }

    @Bean
    @Scope("cucumber-glue")
    public UniversityHelper universityHelper() {
        return new UniversityHelper();
    }

    @Bean
    @Scope("cucumber-glue")
    public TeacherHelper teacherHelper() {
        return new TeacherHelper();
    }

    @Bean
    @Scope("cucumber-glue")
    public StudentHelper studentHelper() {
        return new StudentHelper();
    }


}
