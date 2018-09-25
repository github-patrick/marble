package com.example.marble.config;

import org.fluttercode.datafactory.impl.DataFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    DataFactory dataFactory() {
        return new DataFactory();
    }
}
