package com.ju5.JUNIT_TESTING.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapping {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
