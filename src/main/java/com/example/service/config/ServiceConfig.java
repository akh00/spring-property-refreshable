package com.example.service.config;

import com.example.service.SomeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig
{
    @Bean
    public SomeService someService(SomeProperties someProperties)
    {
        return new SomeService(someProperties);
    }
}
