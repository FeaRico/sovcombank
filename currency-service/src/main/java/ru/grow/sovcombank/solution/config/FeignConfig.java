package ru.grow.sovcombank.solution.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("apikey", "1Z1HkUb2tNsRjf6QKDyMscj7Q3rzHeP0");
    }
}
